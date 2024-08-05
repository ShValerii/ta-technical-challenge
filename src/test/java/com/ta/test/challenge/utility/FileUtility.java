package com.ta.test.challenge.utility;

import java.io.File;
import java.util.regex.Pattern;

public final class FileUtility {

  public static String extractFileVersion(File root, Pattern p) {
    if (!root.isDirectory()) {
      throw new IllegalArgumentException(root + " is no directory.");
    }
    File[] files = root.listFiles(f -> p.matcher(f.getName()).matches());
    if (files != null && files.length > 0) {
      return files[0].getName().split("-")[1].substring(1);
    } else {
      return null;
    }
  }

  public static File firstFileMatching(File root, Pattern p) {
    if (!root.isDirectory()) {
      throw new IllegalArgumentException(root + " is no directory.");
    }
    File[] files = root.listFiles(f -> p.matcher(f.getName()).matches());
    return (files != null && files.length > 0) ? files[0] : null;
  }

  public static File[] filesMatching(File root, Pattern p) {
    if (!root.isDirectory()) {
      throw new IllegalArgumentException(root + " is no directory.");
    }
    File[] files = root.listFiles(f -> p.matcher(f.getName()).matches());
    return (files != null && files.length > 0) ? files : null;
  }
}
