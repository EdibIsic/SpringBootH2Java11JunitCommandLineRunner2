package com.creditsuisse.demo;

import com.creditsuisse.demo.model.EventDataDb;
import com.creditsuisse.demo.model.EventDataJson;
import com.creditsuisse.demo.repository.EventDataRepository;
import com.creditsuisse.demo.service.JsonReaderService;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class DemoApplicationTests {

    private EventDataJson expectedEventData0;
    private EventDataJson expectedEventData1;
    private EventDataJson expectedEventData2;
    private EventDataJson expectedEventData3;
    private EventDataJson expectedEventData4;
    private EventDataJson expectedEventData5;
    static Logger logger = LoggerFactory.getLogger(DemoApplication.class);
    @Autowired
    private EventDataRepository repository;

    @Autowired
    private JsonReaderService jReader;

    @Before
    public void setUp() {

    }

    @Test
    public void readJsonEventData_then_test_convert_to_database_entity() throws IOException {
        BufferedReader reader = Files.newBufferedReader(Paths.get("src/main/resources/" + "logfile.txt"));
        List<EventDataJson> jsonEventDataList = jReader.readEventDataFromJson(reader);
        EventDataJson startedEventDataById = jsonEventDataList.stream().filter((item) -> item.getId().equals("scsmbstgrb")).filter((item) -> item.getState().equals("STARTED")).findAny().orElse(null);
        EventDataJson finishedEventDataById = jsonEventDataList.stream().filter((item) -> item.getId().equals("scsmbstgrb")).filter((item) -> item.getState().equals("FINISHED")).findAny().orElse(null);
        long duration = finishedEventDataById.getTimestamp() - startedEventDataById.getTimestamp();
        //assert
        Assertions.assertTrue(duration == 3);

        EventDataJson startedEventDataById0 = jsonEventDataList.stream().filter((item) -> item.getId().equals("scsmbstgrc")).filter((item) -> item.getState().equals("STARTED")).findAny().orElse(null);
        EventDataJson finishedEventDataById0 = jsonEventDataList.stream().filter((item) -> item.getId().equals("scsmbstgrc")).filter((item) -> item.getState().equals("FINISHED")).findAny().orElse(null);
        long duration0 = finishedEventDataById0.getTimestamp() - startedEventDataById0.getTimestamp();
        Assertions.assertTrue(duration0 == 8);

    }

    @Test
    void contextLoads() {
    }

    @Test
    public void whenReadJson_from_StringReader_should_then_read_6_EventData() throws IOException {

        //expected
        expectedEventData0 = new EventDataJson("scsmbstgra", "STARTED", "APPLICATION_LOG", "12345", 1491377495212L);
        expectedEventData1 = new EventDataJson("scsmbstgrb", "STARTED", null, null, 1491377495213L);
        expectedEventData2 = new EventDataJson("scsmbstgrc", "FINISHED", null, null, 1491377495218L);
        expectedEventData3 = new EventDataJson("scsmbstgra", "FINISHED", "APPLICATION_LOG", "12345", 1491377495217L);
        expectedEventData4 = new EventDataJson("scsmbstgrc", "STARTED", null, null, 1491377495210L);
        expectedEventData5 = new EventDataJson("scsmbstgrb", "FINISHED", null, null, 1491377495216L);

        List<EventDataJson> expectedEventDataList = new ArrayList<EventDataJson>();
        expectedEventDataList.add(expectedEventData0);
        expectedEventDataList.add(expectedEventData1);
        expectedEventDataList.add(expectedEventData2);
        expectedEventDataList.add(expectedEventData3);
        expectedEventDataList.add(expectedEventData4);
        expectedEventDataList.add(expectedEventData5);


//        actual
        String actualEventData0 = "[\n" +
                "\t{\n" +
                "\t\t\"id\": \"scsmbstgra\",\n" +
                "\t\t\"state\": \"STARTED\",\n" +
                "\t\t\"type\": \"APPLICATION_LOG\",\n" +
                "\t\t\"host\": \"12345\",\n" +
                "\t\t\"timestamp\": 1491377495212\n" +
                "\t}\n";
        String actualEventData1 =
                "\t{\n" +
                        "\t\t\"id\": \"scsmbstgrb\",\n" +
                        "\t\t\"state\": \"STARTED\",\n" +
                        "\t\t\"timestamp\": 1491377495213\t\n" +
                        "\t}\n";
        String actualEventData2 =
                "{\n" +
                        "\t\t\"id\": \"scsmbstgrc\",\n" +
                        "\t\t\"state\": \"FINISHED\",\n" +
                        "\t\t\"timestamp\": 1491377495218\n" +
                        "\t}";
        String actualEventData3 =
                "{\n" +
                        "\t\t\"id\": \"scsmbstgra\",\n" +
                        "\t\t\"state\": \"FINISHED\",\n" +
                        "\t\t\"type\": \"APPLICATION_LOG\",\n" +
                        "\t\t\"host\": \"12345\",\n" +
                        "\t\t\"timestamp\": 1491377495217\n" +
                        "\t}";
        String actualEventData4 =
                "{\n" +
                        "\t\t\"id\": \"scsmbstgrc\",\n" +
                        "\t\t\"state\": \"STARTED\",\n" +
                        "\t\t\"timestamp\": 1491377495210\n" +
                        "\t}";
        String actualEventData5 =
                "{\n" +
                        "\t\t\"id\": \"scsmbstgrb\",\n" +
                        "\t\t\"state\": \"FINISHED\",\n" +
                        "\t\t\"timestamp\": 1491377495216\n" +
                        "\t}" +
                        "\n]";

        StringReader strReader = new StringReader(actualEventData0 + "," + actualEventData1 + "," + actualEventData2 + "," + actualEventData3 + "," + actualEventData4 + "," + actualEventData5);
        List<EventDataJson> actualEventDataArray = jReader.readEventDataFromJson(strReader);
//Assert
        Assertions.assertEquals(expectedEventDataList, actualEventDataArray);
        Assertions.assertTrue(actualEventDataArray.containsAll(expectedEventDataList));

    }

    @Test
    public void whenloadJson_then_should_be_equal_to_createdEventData() throws IOException {
        BufferedReader reader = Files.newBufferedReader(Paths.get("src/main/resources/" + "logfile.txt"));
        //expected
        expectedEventData0 = new EventDataJson("scsmbstgra", "STARTED", "APPLICATION_LOG", "12345", 1491377495212L);
        expectedEventData1 = new EventDataJson("scsmbstgrb", "STARTED", null, null, 1491377495213L);
        expectedEventData2 = new EventDataJson("scsmbstgrc", "FINISHED", null, null, 1491377495218L);
        expectedEventData3 = new EventDataJson("scsmbstgra", "FINISHED", "APPLICATION_LOG", "12345", 1491377495217L);
        expectedEventData4 = new EventDataJson("scsmbstgrc", "STARTED", null, null, 1491377495210L);
        expectedEventData5 = new EventDataJson("scsmbstgrb", "FINISHED", null, null, 1491377495216L);

        List<EventDataJson> expectedEventDataList = new ArrayList<EventDataJson>();
        expectedEventDataList.add(expectedEventData0);
        expectedEventDataList.add(expectedEventData1);
        expectedEventDataList.add(expectedEventData2);
        expectedEventDataList.add(expectedEventData3);
        expectedEventDataList.add(expectedEventData4);
        expectedEventDataList.add(expectedEventData5);
//actual
        List<EventDataJson> jsonEventDataList = jReader.readEventDataFromJson(reader);

        //assert
        Assertions.assertEquals(jsonEventDataList, expectedEventDataList);
        Assertions.assertTrue(expectedEventDataList.size() == jsonEventDataList.size());
        Assertions.assertTrue(jsonEventDataList.containsAll(expectedEventDataList));
    }

    @Test
    public void whenParseJson_thenTransform_and_save_to_db() throws IOException {
        BufferedReader reader = Files.newBufferedReader(Paths.get("src/main/resources/" + "logfile.txt"));
        List<EventDataJson> eventDataList1 = jReader.readEventDataFromJson(reader);
        if (jReader.mappingToDatabase(eventDataList1)) {
            EventDataDb eventDataFromDb = this.repository.findAllById("scsmbstgra");
            Assertions.assertTrue(eventDataFromDb.getType().equals("APPLICATION_LOG"));
            Assertions.assertTrue(eventDataFromDb.getHost().equals("12345"));
            Assertions.assertTrue(eventDataFromDb.getAlert().equals(true));
            Assertions.assertTrue(eventDataFromDb.getDuration() == 5);
            logger.info("Assert successfully accomplished");
        } else
            logger.error("Could not persist eventData to DB Error");
    }
}
