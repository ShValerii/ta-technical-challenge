package com.ta.test.challenge.util;

import java.io.File;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public final class FileUtils {

  public static final Pattern SHIFT_VERSION_PATTERN = Pattern.compile("^shift-v(\\d+.\\d+.\\d+.\\d+).*$");

  public static String extractShiftFileVersion(File root) {
    if (root == null || !root.isDirectory()) {
      throw new IllegalArgumentException(root + " is no directory.");
    }
    return Stream.of(root.listFiles()).map(file -> {
      var matcher = SHIFT_VERSION_PATTERN.matcher(file.getName());
      if (matcher.matches()) {
        return matcher.group(1);
      }
      return null;
    }).filter(Objects::nonNull).findAny().orElse("");
  }

  public static File firstFileMatching(File root, Pattern p) {
    if (root == null || !root.isDirectory()) {
      throw new IllegalArgumentException(root + " is no directory.");
    }
    File[] files = root.listFiles(f -> p.matcher(f.getName()).matches());
    return (files != null && files.length > 0) ? files[0] : null;
  }

  public static File[] filesMatching(File root, Pattern p) {
    if (root == null || !root.isDirectory()) {
      throw new IllegalArgumentException(root + " is no directory.");
    }
    File[] files = root.listFiles(f -> p.matcher(f.getName()).matches());
    return (files != null && files.length > 0) ? files : new File[]{};
  }
}
