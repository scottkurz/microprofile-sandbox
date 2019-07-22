package io.openliberty.boost.runtimes.boosters;

import static boost.common.config.ConfigConstants.*;
import static org.junit.Assert.assertTrue;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.RestoreSystemProperties;
import org.junit.rules.TemporaryFolder;
import boost.runtimes.openliberty.LibertyServerConfigGenerator;

import boost.common.BoostLoggerI;
import io.openliberty.boost.runtimes.utils.BoosterUtil;
import io.openliberty.boost.runtimes.utils.CommonLogger;
import io.openliberty.boost.runtimes.utils.ConfigFileUtils;
import boost.runtimes.openliberty.boosters.*;

public class CDIBoosterTest {

    @Rule
    public TemporaryFolder outputDir = new TemporaryFolder();

    @Rule
    public final RestoreSystemProperties restoreSystemProperties = new RestoreSystemProperties();

    // private Map<String, String> getCDIDependency(String version) throws
    // BoostException {
    // return
    // BoosterUtil.createDependenciesWithBoosterAndVersion(CDIBoosterConfig.class,
    // version);
    // }

    BoostLoggerI logger = CommonLogger.getInstance();

    /**
     * Test that the cdi-1.2 feature is added to server.xml when the CDI booster
     * version is set to 0.1.2-SNAPSHOT
     * 
     */
    @Test
    public void testCDIBoosterFeature_12() throws Exception {

        LibertyServerConfigGenerator serverConfig = new LibertyServerConfigGenerator(
                outputDir.getRoot().getAbsolutePath(), logger);

        LibertyCDIBoosterConfig libCDIConfig = new LibertyCDIBoosterConfig(BoosterUtil.createDependenciesWithBoosterAndVersion(LibertyCDIBoosterConfig.class, "0.1.2-SNAPSHOT"), logger);
        
        serverConfig.addFeature(libCDIConfig.getFeature());
        serverConfig.writeToServer();

        String serverXML = outputDir.getRoot().getAbsolutePath() + "/server.xml";
        boolean featureFound = ConfigFileUtils.findStringInServerXml(serverXML, "<feature>" + CDI_12 + "</feature>");

        assertTrue("The " + CDI_12 + " feature was not found in the server configuration", featureFound);

    }

        /**
     * Test that the cdi-2.0 feature is added to server.xml when the CDI booster
     * version is set to 0.2-SNAPSHOT
     * 
     */
    @Test
    public void testCDIBoosterFeature_20() throws Exception {

        LibertyServerConfigGenerator serverConfig = new LibertyServerConfigGenerator(
                outputDir.getRoot().getAbsolutePath(), logger);

        LibertyCDIBoosterConfig libCDIConfig = new LibertyCDIBoosterConfig(BoosterUtil.createDependenciesWithBoosterAndVersion(LibertyCDIBoosterConfig.class, "0.2.0-SNAPSHOT"), logger);

        serverConfig.addFeature(libCDIConfig.getFeature());
        serverConfig.writeToServer();

        String serverXML = outputDir.getRoot().getAbsolutePath() + "/server.xml";
        boolean featureFound = ConfigFileUtils.findStringInServerXml(serverXML, "<feature>" + CDI_20 + "</feature>");

        assertTrue("The " + CDI_20 + " feature was not found in the server configuration", featureFound);

    }

}
