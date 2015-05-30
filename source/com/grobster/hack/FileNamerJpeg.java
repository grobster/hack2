package com.grobster.hack;

import java.nio.file.*;
import java.io.*;

public class FileNamerJpeg extends FileNamer {
	public FileNamerJpeg() {
		setFileEnding(FileNamer.JPEG_ENDING);
		setFileNameBase(FileNamer.IMG_BASE_FILE_NAME);
	}
}