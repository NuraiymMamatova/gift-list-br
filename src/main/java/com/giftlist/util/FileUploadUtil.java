package com.giftlist.util;

import lombok.experimental.UtilityClass;
import org.hibernate.query.sqm.produce.function.FunctionArgumentException;
import org.springframework.web.multipart.MultipartFile;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@UtilityClass
public class FileUploadUtil {

    public static final long MAX_FILE_SIZE = 2 * 1024 * 1024/*10 * 1024 * 1024*/;

    public static final String IMAGE_PATTERN = "([^\\s]+(\\.(?i)(jpg|png|gif|bmp))$)"/*"(.*?)\\.(jpg|jpeg|png|gif)"*/;

    public static final String DATE_FORMAT = "yyyyMMddHHmmss";

    public static final String FILE_NAME_FORMAT = "%s_%s";

    public static boolean isAllowedExtension(final String fileName, final String pattern) {
        final Matcher matcher = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE).matcher(fileName);
        return matcher.matches();
    }

    public static void assertAllowed(MultipartFile file, String pattern) {
        final long fileSize = file.getSize();
        if (fileSize > MAX_FILE_SIZE) {
            throw new FunctionArgumentException("File is too large to be accepted");
        }
        if (!isAllowedExtension(file.getOriginalFilename(), pattern)) {
            throw new FunctionArgumentException("File extension is not supported");
        }
    }

    public static String getFileName(final String fileName) {
        final DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        final String date = dateFormat.format(System.currentTimeMillis());
        return String.format(FILE_NAME_FORMAT, fileName, date);
    }

}
