package org.fileOrganizer;

import org.apache.commons.io.FilenameUtils;
import java.util.HashMap;
import java.util.Map;

public class FileCategorize {

    private static final Map<String, String> EXTENSION_DIRECTORY_MAP = new HashMap<String, String>();

    static {
        // Mapeamento das extensões de arquivo para os diretórios correspondentes
        EXTENSION_DIRECTORY_MAP.put("jpg", "/home/cleverson/Desktop/organizer/imagens");
        EXTENSION_DIRECTORY_MAP.put("jpeg", "/home/cleverson/Desktop/organizer/imagens");
        EXTENSION_DIRECTORY_MAP.put("png", "/home/cleverson/Desktop/organizer/imagens");
        EXTENSION_DIRECTORY_MAP.put("gif", "/home/cleverson/Desktop/organizer/imagens");
        EXTENSION_DIRECTORY_MAP.put("bmp", "/home/cleverson/Desktop/organizer/imagens");
        EXTENSION_DIRECTORY_MAP.put("pdf", "/home/cleverson/Desktop/organizer/documentos");
        EXTENSION_DIRECTORY_MAP.put("mp3", "/home/cleverson/Desktop/organizer/audio");
        EXTENSION_DIRECTORY_MAP.put("wav", "/home/cleverson/Desktop/organizer/audio");
        EXTENSION_DIRECTORY_MAP.put("flac", "/home/cleverson/Desktop/organizer/audio");
    }

    public static String categorizeFile(String filePath) {
        String extension = FilenameUtils.getExtension(filePath);
        return EXTENSION_DIRECTORY_MAP.getOrDefault(extension, "/home/cleverson/Desktop/organizer/outros");
    }
}
