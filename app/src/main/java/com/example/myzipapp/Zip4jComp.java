package com.example.myzipapp;

import android.util.Log;

import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;

import java.io.File;

public class Zip4jComp {
      public static void compress(String path) {
          ZipFile zipfile = new ZipFile(path + ".zip");
          File file = new File(path);
          try {
              zipfile.addFolder(file);
          } catch (ZipException e) {
              Log.d("dimode", "compress: ");
          }

      }
}
