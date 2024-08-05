package com.ta.test.challenge.utility;

import java.io.File;
import java.util.Objects;
import java.util.regex.Pattern;

import org.junit.jupiter.api.Assertions;

public final class FileUtility {

  //TODO rewrite using regexp
  public static String extractFileVersion(String downloadFolder, String fileName) {
    File folder = new File(downloadFolder);
    File[] listOfFiles = folder.listFiles();
    String downloadedVersion = "";
    for (File file : Objects.requireNonNull(listOfFiles)) {
      if (file.isFile() && file.getName().contains(fileName)) {
        downloadedVersion = file.getName().split("-")[1];
        downloadedVersion = downloadedVersion.substring(1);
        Assertions.assertTrue(file.exists(), "Download not successful.");
        break;
      }
    }
    return downloadedVersion;
  }

  public static File firstFileMatching(File root, Pattern p) {
    if (!root.isDirectory()) {
      throw new IllegalArgumentException(root + " is no directory.");
    }
    File[] files = root.listFiles(f -> p.matcher(f.getName()).matches());
    return (files != null && files.length > 0) ? files[0] : null;
  }
}
