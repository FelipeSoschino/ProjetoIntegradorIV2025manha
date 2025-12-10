package com.senac.forum_musicos.config;//package com.senac.forum_musicos.config;
//import jakarta.persistence.AttributeConverter;
//import jakarta.persistence.Converter;
//import java.io.File;
//
//@Converter(autoApply = true)
//public class FileAttributeConverter implements AttributeConverter<File, String> {
//
//    @Override
//    public String convertToDatabaseColumn(File file) {
//        return (file != null ? file.getAbsolutePath() : null);
//    }
//
//    @Override
//    public File convertToEntityAttribute(String path) {
//        return (path != null ? new File(path) : null);
//    }
//}