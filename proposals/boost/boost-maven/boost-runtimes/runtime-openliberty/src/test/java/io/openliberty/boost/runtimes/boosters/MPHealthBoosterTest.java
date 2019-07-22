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

public class MPHealthBoosterTest {

    @Rule
    public TemporaryFolder outputDir = new TemporaryFolder();

    @Rule
    public final RestoreSystemProperties restoreSystemProperties = new RestoreSystemProperties();

    BoostLoggerI logger = CommonLogger.getInstance();

    /**
     * Test that the mpHealth-1.0 feature is added to server.xml when the MPHealth
     * booster version is set to 0.2-SNAPSHOT
     * 
     */
    @Test
    public void testMPHealthBoosterFeature10() throws Exception {

        LibertyServerConfigGenerator serverConfig = new LibertyServerConfigGenerator(
                outputDir.getRoot().getAbsolutePath(), logger);

        LibertyMPHealthBoosterConfig libMPHealthConfig = new LibertyMPHealthBoosterConfig(BoosterUtil.createDependenciesWithBoosterAndVersion(LibertyMPHealthBoosterConfig.class, "0.1.0-SNAPSHOT"), logger);


        serverConfig.addFeature(libMPHealthConfig.getFeature());
        serverConfig.writeToServer();

        String serverXML = outputDir.getRoot().getAbsolutePath() + "/server.xml";
        boolean featureFound = ConfigFileUtils.findStringInServerXml(serverXML,
                "<feature>" + MPHEALTH_10 + "</feature>");

        assertTrue("The " + MPHEALTH_10 + " feature was not found in the server configuration", featureFound);

    }

}
