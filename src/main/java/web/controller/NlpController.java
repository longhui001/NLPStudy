package web.controller;

import com.sectong.NLPStudy.CoreNLPSegment;
import com.sectong.NLPStudy.DbUility;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

/**
 * Created by xiaodpro on 2017/4/26.
 */
@RestController
@Scope("prototype")
@RequestMapping("/nlp")
public class NlpController {

    private static Logger logger = LoggerFactory.getLogger(NlpController.class);
    private StanfordCoreNLP pipeline;

    public NlpController() throws IOException {
        try {
            Properties prop = new Properties();
            prop.load(NlpController.class.getResourceAsStream("/CoreNLP-chinese.properties"));
            // 载入自定义的Properties文件
            pipeline = new StanfordCoreNLP(prop);
        } catch (IOException e) {
            logger.error("Could not to load resource file.", e);
            throw e;
        }
    }

    @RequestMapping("/segment")
    public ResponseEntity<List<String>> segment(@RequestParam("input") String input){
        List<String> data = CoreNLPSegment.Segment(input, pipeline);
        ResponseEntity<List<String>> resp = new ResponseEntity<List<String>>(data, HttpStatus.OK);
        return resp;
    }
}
