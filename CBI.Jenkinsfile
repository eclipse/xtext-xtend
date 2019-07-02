pipeline {
  agent {
    kubernetes {
      label 'xtext-xtend-' + env.BRANCH_NAME + '-' + env.BUILD_NUMBER
      defaultContainer 'xtext-buildenv'
      yaml '''
apiVersion: v1
kind: Pod
spec:
  containers:
  - name: jnlp
    image: 'eclipsecbi/jenkins-jnlp-agent'
    args: ['\$(JENKINS_SECRET)', '\$(JENKINS_NAME)']
    resources:
      limits:
        memory: "0.4Gi"
        cpu: "0.2"
      requests:
        memory: "0.4Gi"
        cpu: "0.2"
    volumeMounts:
    - mountPath: /home/jenkins/.ssh
      name: volume-known-hosts
  - name: xtext-buildenv
    image: docker.io/smoht/xtext-buildenv:0.7
    tty: true
    resources:
      limits:
        memory: "3.6Gi"
        cpu: "1.0"
      requests:
        memory: "3.6Gi"
        cpu: "1.0"
    volumeMounts:
    - name: settings-xml
      mountPath: /home/jenkins/.m2/settings.xml
      subPath: settings.xml
      readOnly: true
    - name: m2-repo
      mountPath: /home/jenkins/.m2/repository
    - name: volume-known-hosts
      mountPath: /home/jenkins/.ssh
  volumes:
  - name: volume-known-hosts
    configMap:
      name: known-hosts
  - name: settings-xml
    configMap: 
      name: m2-dir
      items:
      - key: settings.xml
        path: settings.xml
  - name: m2-repo
    emptyDir: {}
    '''
    }
  }
  
  parameters {
    choice(name: 'target_platform', choices: ['oxygen', 'photon', 'r201812', 'r201809', 'r201903', 'r201906', 'latest'], description: 'Which Target Platform should be used?')
  }

  options {
    buildDiscarder(logRotator(numToKeepStr:'5'))
    disableConcurrentBuilds()
    timeout(time: 240, unit: 'MINUTES')
    timestamps()
  }

  // https://jenkins.io/doc/book/pipeline/syntax/#triggers
  triggers {
    pollSCM('H/5 * * * *')
  }

  stages {
    stage('Checkout') {
      steps {
        checkout scm
        
        script {
          if (params.target_platform == 'latest') {
            currentBuild.displayName = "#${BUILD_NUMBER}(4.13)"
          } else if (params.target_platform == 'r201906') {
            currentBuild.displayName = "#${BUILD_NUMBER}(4.12)"
          } else if (params.target_platform == 'r201903') {
            currentBuild.displayName = "#${BUILD_NUMBER}(4.11)"
          } else if (params.target_platform == 'r201812') {
            currentBuild.displayName = "#${BUILD_NUMBER}(4.10)"
          } else if (params.target_platform == 'r201809') {
            currentBuild.displayName = "#${BUILD_NUMBER}(4.9)"
          } else if (params.target_platform == 'photon') {
            currentBuild.displayName = "#${BUILD_NUMBER}(4.8)"
          } else {
            currentBuild.displayName = "#${BUILD_NUMBER}(4.7)"
          }
        }
        sh '''
            sed_inplace() {
                if [[ "$OSTYPE" == "darwin"* ]]; then
                    sed -i '' "$@"
                else
                    sed -i "$@" 
                fi    
            }
            
            targetfiles="$(find releng -type f -iname '*.target')"
            for targetfile in $targetfiles
            do
                echo "Redirecting target platforms in $targetfile to $JENKINS_URL"
                sed_inplace "s?<repository location=\\".*/job/\\([^/]*\\)/job/\\([^/]*\\)/?<repository location=\\"$JENKINS_URL/job/\\1/job/\\2/?" $targetfile
            done
        '''
      }
    }

    stage('Gradle Build') {
      steps {
        sh "./1-gradle-build.sh"
        step([$class: 'JUnitResultArchiver', testResults: '**/build/test-results/test/*.xml'])
      }
    }

    stage('Maven Build & Test') {
      parallel {
        stage('Maven Plugin Build') {
          steps {
            sh """
              ./2-maven-plugin-build.sh -s /home/jenkins/.m2/settings.xml --local-repository=/home/jenkins/.m2/repository
            """
            step([$class: 'JUnitResultArchiver', testResults: '**/target/surefire-reports/*.xml'])
          } // END steps
        } // END stage
        stage('Maven Tycho Build') {
          steps {
            sh """
              /home/vnc/.vnc/xstartup.sh
              ./3-maven-tycho-build.sh -s /home/jenkins/.m2/settings.xml --tp=${params.target_platform} --local-repository=/home/jenkins/.m2/repository
            """
            step([$class: 'JUnitResultArchiver', testResults: '**/target/surefire-reports/*.xml'])
          }// END steps
        } // END stage
      } // END parallel
    } // END stage
  } // END stages

  post {
    success {
      archiveArtifacts artifacts: 'build/**'
    }
    failure {
      archiveArtifacts artifacts: 'org.eclipse.xtend.ide.swtbot.tests/screenshots/**, build/**, **/target/work/data/.metadata/.log, **/hs_err_pid*.log'
    }
    changed {
      script {
        def envName = ''
        if (env.JENKINS_URL.contains('ci.eclipse.org/xtext')) {
          envName = ' (JIPP)'
        } else if (env.JENKINS_URL.contains('ci-staging.eclipse.org/xtext')) {
          envName = ' (JIRO)'
        } else if (env.JENKINS_URL.contains('jenkins.eclipse.org/xtext')) {
          envName = ' (CBI)'
        } else if (env.JENKINS_URL.contains('typefox.io')) {
          envName = ' (TF)'
        }
        
        def curResult = currentBuild.currentResult
        def color = '#00FF00'
        if (curResult == 'SUCCESS' && currentBuild.previousBuild != null) {
          curResult = 'FIXED'
        } else if (curResult == 'UNSTABLE') {
          color = '#FFFF00'
        } else if (curResult == 'FAILURE') {
          color = '#FF0000'
        }
        
        slackSend message: "${curResult}: <${env.BUILD_URL}|${env.JOB_NAME}#${env.BUILD_NUMBER}${envName}>", botUser: true, channel: 'xtext-builds', color: "${color}"
      }
    }
  }
}
