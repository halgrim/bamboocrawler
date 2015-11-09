package pl.hybris.bamboo.enums;

import java.util.stream.Stream;

/**
 * Created by i323728 on 11/6/15.
 */
public enum MetadataAntTask
{

    HEADER,
    TASK_DESCRIPTION,
    TASK_DISABLED,
    EXECUTABLE_VERSION,
    BUILD_FILE,
    TARGET,
    BUILD_SDK,
    ENV_VARIABLES,
    WORKING_SUB_DIR,
    BUILD_PRODUCE_TEST_RESULTS,
    CUSTOM_TEST_RESULTS_DIR,
    SCREENSHOT_FILE_NAME,
    BUILD_KEY;

    public static String[] properties() {
        return Stream.of(MetadataAntTask.values()).map(MetadataAntTask::name).toArray(String[]::new);
    }
}
