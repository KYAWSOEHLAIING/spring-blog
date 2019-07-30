package com.example.demo.model;

import com.example.demo.service.BeanUtil;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.ocpsoft.prettytime.PrettyTime;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

@Entity
@NoArgsConstructor
@Data
public class Post implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tag;
    @Lob
    @Column(length = 100000)
    private String body;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate lastUpdated;
    @ManyToOne
    private Author author;

    public Post(String tag, String body, LocalDate lastUpdated) {
        this.tag = tag;
        this.body = body;
        this.lastUpdated = lastUpdated;
    }
    public String showPrettyTime(){
        PrettyTime prettyTime = BeanUtil.prettyTime(PrettyTime.class);
        String pretty = "";
        try {
            pretty = prettyTime.format(convertToDate(lastUpdated));
        } catch (Exception e) {

        }
        return pretty;
    }

    public Date convertToDate(LocalDate localDate) throws  Exception{
        return new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());

    }

}
