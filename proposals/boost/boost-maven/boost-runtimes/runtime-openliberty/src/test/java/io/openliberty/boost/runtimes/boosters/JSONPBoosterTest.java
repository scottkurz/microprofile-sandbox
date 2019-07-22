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

public class JSONPBoosterTest {

    @Rule
    public TemporaryFolder outputDir = new TemporaryFolder();

    @Rule
    public final RestoreSystemProperties restoreSystemProperties = new RestoreSystemProperties();

    BoostLoggerI logger = CommonLogger.getInstance();

    /**
     * Test that the jsonp-1.0 feature is added to server.xml when the jsonp booster
     * version is set to 0.1.0-SNAPSHOT
     * 
     */
    @Test
    public void testJSONPBoosterFeature10() throws Exception {

        LibertyServerConfigGenerator serverConfig = new LibertyServerConfigGenerator(
                outputDir.getRoot().getAbsolutePath(), logger);

        LibertyJSONPBoosterConfig libJSONPConfig = new LibertyJSONPBoosterConfig(BoosterUtil.createDependenciesWithBoosterAndVersion(LibertyJSONPBoosterConfig.class, "0.1.0-SNAPSHOT"), logger);


        serverConfig.addFeature(libJSONPConfig.getFeature());
        serverConfig.writeToServer();

        String serverXML = outputDir.getRoot().getAbsolutePath() + "/server.xml";
        boolean featureFound = ConfigFileUtils.findStringInServerXml(serverXML, "<feature>" + JSONP_10 + "</feature>");

        assertTrue("The " + JSONP_10 + " feature was not found in the server configuration", featureFound);

    }

       /**
     * Test that the jsonp-1.1 feature is added to server.xml when the jsonp booster
     * version is set to 0.1.1-SNAPSHOT
     * 
     */
    @Test
    public void testJSONPBoosterFeature11() throws Exception {

        LibertyServerConfigGenerator serverConfig = new LibertyServerConfigGenerator(
                outputDir.getRoot().getAbsolutePath(), logger);

        LibertyJSONPBoosterConfig libJSONPConfig = new LibertyJSONPBoosterConfig(BoosterUtil.createDependenciesWithBoosterAndVersion(LibertyJSONPBoosterConfig.class, "0.1.1-SNAPSHOT"), logger);

        serverConfig.addFeature(libJSONPConfig.getFeature());
        serverConfig.writeToServer();

        String serverXML = outputDir.getRoot().getAbsolutePath() + "/server.xml";
        boolean featureFound = ConfigFileUtils.findStringInServerXml(serverXML, "<feature>" + JSONP_11 + "</feature>");

        assertTrue("The " + JSONP_11 + " feature was not found in the server configuration", featureFound);

    }

}