package pl.hybris.bamboo.persistence;

import org.springframework.data.annotation.Id;

/**
 * Created by i323728 on 11/9/15.
 */
public class AntTask
{

    @Id
    private String id;
    private String header;
    private String taskDescription;
    private String taskDisabled;
    private String executableVersion;
    private String buildFile;
    private String target;
    private String buildSdk;
    private String envVariables;
    private String workingSubDir;
    private String buildProduceTestResults;
    private String customTestResultsDir;
    private String screenshotFileName;
    private String buildKey;


    public AntTask() {}

    public AntTask(String header, String taskDescription, String taskDisabled, String executableVersion, String buildFile, String target, String buildSdk, String envVariables, String workingSubDir, String buildProduceTestResults, String customTestResultsDir, String screenshotFileName, String buildKey)
    {
        this.header = header;
        this.taskDescription = taskDescription;
        this.taskDisabled = taskDisabled;
        this.executableVersion = executableVersion;
        this.buildFile = buildFile;
        this.target = target;
        this.buildSdk = buildSdk;
        this.envVariables = envVariables;
        this.workingSubDir = workingSubDir;
        this.buildProduceTestResults = buildProduceTestResults;
        this.customTestResultsDir = customTestResultsDir;
        this.screenshotFileName = screenshotFileName;
        this.buildKey = buildKey;
    }

    public String getId()
    {
        return id;
    }

    public String getHeader()
    {
        return header;
    }

    public String getTaskDescription()
    {
        return taskDescription;
    }

    public String getTaskDisabled()
    {
        return taskDisabled;
    }

    public String getExecutableVersion()
    {
        return executableVersion;
    }

    public String getBuildFile()
    {
        return buildFile;
    }

    public String getTarget()
    {
        return target;
    }

    public String getBuildSdk()
    {
        return buildSdk;
    }

    public String getEnvVariables()
    {
        return envVariables;
    }

    public String getWorkingSubDir()
    {
        return workingSubDir;
    }

    public String getBuildProduceTestResults()
    {
        return buildProduceTestResults;
    }

    public String getCustomTestResultsDir()
    {
        return customTestResultsDir;
    }

    public String getBuildKey()
    {
        return buildKey;
    }
}
