package org.eclipse.xtend.maven;

import static com.google.common.collect.Lists.newArrayList;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Arrays;

import org.apache.maven.it.VerificationException;
import org.apache.maven.it.Verifier;
import org.apache.maven.it.util.ResourceExtractor;
import org.apache.maven.shared.utils.io.FileUtils;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Ignore;

import com.google.common.base.Objects;

public class XtendCompilerMojoIT {

	private static String ROOT = "/it/compile";
	
	@BeforeClass
	static public void setUpOnce() throws IOException, VerificationException {
		MavenVerifierUtil.checkMavenExecutable(ROOT);
	}

	@Test
	public void fileSystemAccess() throws Exception {
		deleteFileIfExist("myusercode/UserCode.css");
		deleteFileIfExist("com/itemis/myusercode/UserCode2.css");

		Verifier annotationVerifier = newVerifier(ROOT + "/filesystemaccess");
		annotationVerifier.setDebug(true);
		annotationVerifier.executeGoal("install");
		annotationVerifier.verifyErrorFreeLog();

		Verifier clientVerifier = newVerifier(ROOT + "/filesystemaccess-client");
		clientVerifier.setDebug(true);
		clientVerifier.executeGoal("compile");
		clientVerifier.verifyErrorFreeLog();
	}

	private void deleteFileIfExist(String path) throws URISyntaxException {
		URL userCodeURL = getClass().getResource(ROOT + "/filesystemaccess-client/src/main/java/" + path);
		if (userCodeURL != null) {
			new File(userCodeURL.toURI()).delete();
		}
	}

	
	private void verifyErrorFreeLog(String pathToTestProject) throws IOException, VerificationException {
		verifyErrorFreeLog(pathToTestProject, "verify");
	}

	private void verifyErrorFreeLog(String pathToTestProject, String goal) throws IOException, VerificationException {
		Verifier verifier = newVerifier(pathToTestProject);
		verifier.executeGoal(goal);
		verifier.verifyErrorFreeLog();
		verifier.resetStreams();
	}

	private Verifier newVerifier(String pathToTestProject) throws IOException, VerificationException {
		File testDir = ResourceExtractor.simpleExtractResources(getClass(), pathToTestProject);
		Verifier verifier = new Verifier(testDir.getAbsolutePath());
		String localRepo = Paths.get("../.m2/repository/").toAbsolutePath().normalize().toString();
		verifier.setLocalRepo(localRepo);
		verifier.setDebug(true);
		verifier.setMavenDebug(true);
		// verifier.setDebugJvm(true);
		// verifier.setForkJvm(false);
		return verifier;
	}
	
	private boolean createSymLink(final String linkTarget, final String link) throws IOException {
		File linkFile = new File(link);
		if (linkFile.exists() && isSymlink(linkFile)) {
			return true;
		}

		String[] cmd = { "ln", "-s", linkTarget, link };
		try {
			System.out.println("Exec:" + Arrays.toString(cmd));
			final Process proc = Runtime.getRuntime().exec(cmd);
			int _waitFor = proc.waitFor();
			return (_waitFor == 0);
		} catch (final Exception e) {
			return false;
		}

	}

	private boolean isSymlink(final File file) throws IOException {
		File canon = null;
		String _parent = file.getParent();
		boolean _equals = Objects.equal(_parent, null);
		if (_equals) {
			canon = file;
		} else {
			File _parentFile = file.getParentFile();
			File canonDir = _parentFile.getCanonicalFile();
			String _name = file.getName();
			File _file = new File(canonDir, _name);
			canon = _file;
		}
		File _canonicalFile = canon.getCanonicalFile();
		File _absoluteFile = canon.getAbsoluteFile();
		boolean _equals_1 = _canonicalFile.equals(_absoluteFile);
		return (!_equals_1);
	}

	public void assertFileContainsUTF16(Verifier verifier, String file, String contained) {
		verifier.assertFilePresent(file);
		try {
			String content = FileUtils.fileRead(new File(file), "UTF-16");
			if (!content.contains(contained)) {
				Assert.fail("Content of " + file + " does not contain " + contained + " but: " + content);
			}
		} catch (IOException e) {
			Assert.fail(e.getMessage());
		}
	}
	
	public void assertFileDoesNotContain(Verifier verifier, String file, String contained) {
		verifier.assertFilePresent(file);
		try {
			String content = FileUtils.fileRead(new File(file), "UTF-8");
			if (content.contains(contained)) {
				Assert.fail("Content of " + file + " does contain " + contained + ", but it should not. Contents: " + content);
			}
		} catch (IOException e) {
			Assert.fail(e.getMessage());
		}
	}
}
