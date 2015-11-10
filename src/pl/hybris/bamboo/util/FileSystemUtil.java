package pl.hybris.bamboo.util;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.ArrayUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Created by i323728 on 11/2/15.
 */
public class FileSystemUtil
{

    private final String RESOURCES_JS = "resourcesJS";
    private final String IMAGES_FOLDER = "images";
    private final String LOAD_LIBRARIES = RESOURCES_JS + File.separator+ "JSImageRenderer"+ File.separator+"loadLibs.js";
    private final String SAVE_ELEMENT_TO_FILE = RESOURCES_JS+ File.separator+ "JSImageRenderer"+ File.separator+"saveElementToFile.js";

    public String readLoadLibrariesJS() throws IOException
    {
        return readFile(LOAD_LIBRARIES);
    }

    public String readSaveWebElementToFileJS() throws IOException{
        return readFile(SAVE_ELEMENT_TO_FILE);
    }

    public String generateImageFileName(){
        UUID idOne = UUID.randomUUID();
        return idOne.toString();
    }

    public String buildImagesDestinationPath(){
        String workingDir = System.getProperty("user.dir");
        return workingDir+ File.separator+ IMAGES_FOLDER;
    }

    public boolean checkIfDownloadImageExist(String file)
    {
        File newImage = new File(IMAGES_FOLDER + File.separator + file + ".png");
        if (newImage.length() > 0)
        {
            return true;
        } else {
            return false;
        }

    }

    private String readFile(String file) throws IOException
    {
        Charset cs = Charset.forName("UTF-8");
        FileInputStream stream = new FileInputStream(file);
        try {
            Reader reader = new BufferedReader(new InputStreamReader(stream, cs));
            StringBuilder builder = new StringBuilder();
            char[] buffer = new char[8192];
            int read;
            while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
                builder.append(buffer, 0, read);
            }
            return builder.toString();
        }
        finally {
            stream.close();
        }
    }

    public void dumpAllImageFiles() throws IOException {

        File directory = new File(IMAGES_FOLDER);
        if (!directory.exists()) {
            String message = directory + " does not exist";
            throw new IllegalArgumentException(message);
        }

        if (!directory.isDirectory()) {
            String message = directory + " is not a directory";
            throw new IllegalArgumentException(message);
        }

        File[] files = directory.listFiles();
        if (files == null) {  // null if security restricted
            throw new IOException("Failed to list contents of " + directory);
        }

        files = ArrayUtils.removeElement(files, new File(IMAGES_FOLDER + File.separator+ ".gitignore"));

        IOException exception = null;
        for (File file : files) {
            try {
                FileUtils.forceDelete(file);
            } catch (IOException ioe) {
                exception = ioe;
            }
        }

        if (null != exception) {
            throw exception;
        }

    }

    public void waitForFileToDownloadUsingWatchService(String file) throws IOException
    {
        final Path path = Paths.get(buildImagesDestinationPath());

        try (WatchService watchService = FileSystems.getDefault().newWatchService())
        {
            path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);

            try
            {
                final WatchKey key = watchService.poll(10, TimeUnit.SECONDS);
                // key value can be null if no event was triggered so....
                if (key == null)
                {
                    throw new IOException("Timeout when waiting for a file download after 10 second. FIle: " + file);
                }
                for (WatchEvent<?> watchEvent : key.pollEvents())
                {
                    final Path newFile = (Path) watchEvent.context();
                    if (newFile.endsWith(newFile + ".png"))
                    {
                        CommonUtil.printMessage("New file: " + newFile + ".png");
                    }
                }
            }   catch (InterruptedException e)
            {
                throw new IOException("Timeout when waiting for a file download after 10 second. FIle: " + file);
            }

        }

    }

}
