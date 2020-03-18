package halfmoutainer.purge_local_failed_jar;

import org.apache.commons.io.FileUtils;
import org.apache.maven.artifact.repository.ArtifactRepository;

/*
 * Copyright 2001-2005 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;

import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.plugins.annotations.ResolutionScope;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Map;

/**
 * Goal which touches a timestamp file.
 *
 *
 */
@Mojo(name = "clean", defaultPhase = LifecyclePhase.CLEAN)
public class CleanLocalRepoMojo extends AbstractMojo {
	/**
	 * Location of the file.
	 */
	// @Parameter( defaultValue = "${project.build.directory}", property =
	// "outputDir", required = true )
	// private File outputDirectory;
    /**
     * The local repository, from which to delete artifacts.
     */
    @Parameter( defaultValue = "${localRepository}", readonly = true, required = true )
    private ArtifactRepository localRepository;


	private String[] symbols = new String[] { "lastupdated", "in-progress" };

	private boolean isDownloadFailed(File file) {
		String fileName = file.getName().toLowerCase();
		for (String symbol : symbols) {
			if (fileName.endsWith(symbol)) {
				return true;
			}
		}
		return false;
	}

	public void execute() throws MojoExecutionException {
//		Map context = this.getPluginContext();
//		System.out.println(context);
		HashSet<File> shouldCleans = new HashSet<File>();
		try {
			File purgeDir = new File( localRepository.getBasedir() );
            
			for (File file : FileUtils.listFiles(purgeDir, null, true)) {
				if (file.isFile() && isDownloadFailed(file)) {
					shouldCleans.add(file.getParentFile());
				}
			}
			for(File dir : shouldCleans) {
				FileUtils.deleteDirectory(dir);
				getLog().info(String.format("remove failed jar: %s", dir.getAbsolutePath()) );
			}
		} catch (IOException e) {
			throw new MojoExecutionException("Error clean local repo " , e);
		} finally {
			
		}
	}
}
