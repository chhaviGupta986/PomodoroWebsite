package com.Spring_IA_2.PomodoroWebsite.classes.Music;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.web.multipart.MultipartFile;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class MusicForm extends Music{
    private MultipartFile file;
}
