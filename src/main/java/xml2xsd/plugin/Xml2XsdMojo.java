package xml2xsd.plugin;

import java.util.ArrayList;
import java.util.List;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.codehaus.plexus.util.StringUtils;

import com.thaiopensource.relaxng.translate.Driver;

@Mojo( name = "xsd")
public class Xml2XsdMojo extends AbstractMojo{

	@Parameter( property = "xsd.xmlFiles" )
    private String xmlFiles;
	
	@Parameter( property = "xsd.xsdFile" )
    private String xsdFile;
	
	@Override
	public void execute() throws MojoExecutionException, MojoFailureException {
		getLog().info("xml files:" + xmlFiles);
        getLog().info("xsd file:" + xsdFile);

        String[] cmdArgs = (xmlFiles + ", " + xsdFile).split(",");
        List<String> fileLocations = new ArrayList<String>();
        for (String file : cmdArgs) {
            fileLocations.add("src/main/resources/"+StringUtils.strip(file));
        }

        Driver.main(fileLocations.toArray(new String[fileLocations.size()]));
        getLog().info(xsdFile + " generated.");
    }
		
	}

	

