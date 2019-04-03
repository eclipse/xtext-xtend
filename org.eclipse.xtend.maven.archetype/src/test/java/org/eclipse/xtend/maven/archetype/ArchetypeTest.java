package org.eclipse.xtend.maven.archetype;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import javax.sound.midi.SysexMessage;

import org.apache.maven.it.VerificationException;
import org.apache.maven.it.Verifier;
import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import com.google.common.io.MoreFiles;
import com.google.common.io.RecursiveDeleteOption;

public class ArchetypeTest {
	File tempDir;
	
	@BeforeEach
	public void setUp (TestInfo info) throws VerificationException {
		File javaTempDir = new File (System.getProperty("java.io.tmpdir"));
		tempDir = new File(javaTempDir, info.getTestMethod().get().getName()+System.currentTimeMillis());
		assertTrue(tempDir.mkdir());
	}
	
	@AfterEach
	public void tearDown() throws IOException {
		MoreFiles.deleteRecursively(tempDir.toPath(), RecursiveDeleteOption.ALLOW_INSECURE);
	}
	
	@Test
	public void testCreateArchetype () throws VerificationException, IOException, XmlPullParserException {
		MavenXpp3Reader reader = new MavenXpp3Reader();
		Model pom = reader.read(new FileReader("pom.xml"));
		
		Verifier installArchetypeVerifier = new Verifier(".");
		installArchetypeVerifier.setAutoclean(false);
		installArchetypeVerifier.setSystemProperty("maven.repo.local", new File(".m2/repository").getAbsolutePath());
		installArchetypeVerifier.setSystemProperty("file", pom.getBuild().getFinalName());
		installArchetypeVerifier.setSystemProperty("groupId", pom.getGroupId());
		installArchetypeVerifier.setSystemProperty("artifactId", pom.getArtifactId());
		installArchetypeVerifier.setSystemProperty("version", "IT-SNAPSHOT");
		installArchetypeVerifier.setSystemProperty("generatePom", "true");
		installArchetypeVerifier.executeGoal("install:install-file");

		// Create Verifier for archetype generation
		Verifier generateProjectVerifier = new Verifier(tempDir.getAbsolutePath());
		generateProjectVerifier.setAutoclean(false);

		generateProjectVerifier.setSystemProperty("maven.repo.local", new File(".m2/repository").getAbsolutePath());
		// read project's pom.xml for archetype coordinates
		generateProjectVerifier.setSystemProperty("archetypeGroupId", pom.getGroupId());
		generateProjectVerifier.setSystemProperty("archetypeArtifactId", pom.getArtifactId());
		generateProjectVerifier.setSystemProperty("archetypeVersion", "IT-SNAPSHOT");

		// read archetype.properties and pass properties to archetype:generate goal
		Properties props = new Properties();
		props.load(new FileReader("src/test/resources/projects/first/archetype.properties"));
		props.stringPropertyNames().forEach(p -> generateProjectVerifier.setSystemProperty(p, props.getProperty(p)));
		System.err.println("Creating project from archetype");
		generateProjectVerifier.executeGoal("archetype:generate");
		generateProjectVerifier.assertFilePresent(props.getProperty("artifactId")+"/pom.xml");
		generateProjectVerifier.assertFilePresent(props.getProperty("artifactId")+"/src/main/java/HelloXtend.xtend");
		
		// Create Verifier for the generated project
		Verifier projectVerifier = new Verifier(tempDir.getAbsolutePath()+"/"+props.getProperty("artifactId"));
		System.err.println("Building generated project");
		projectVerifier.executeGoal("verify");
		projectVerifier.assertFilePresent("pom.xml");
	}
	
}
