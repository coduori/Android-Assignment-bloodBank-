package com.example.bloodbank.utilities;

public class URLs {

    // private static final String ROOT_URL = "http://192.168.134.64/api/";
    private static final String TEST_URL = "http://192.168.1.101/limabora/";
    // private static final String TEST_URL = "http://172.16.1.59/limabora/";

    // private static final String ROOT_URL = "http://156.0.232.201/api/";

    // private static final String ROOT_URL = "http://172.16.88.24:8000/api/";

    private static final String ROOT_URL = "http://192.168.42.211:8000/api/";

    public static final String API = "api/";
    public static final String URL_LOGIN = ROOT_URL + "login";
    public static final String URL_REGISTER = ROOT_URL + "register";
    public static final String URL_HOSPITALS = ROOT_URL + "hospitals/";
    public static final String URL_DETAILS = ROOT_URL + "details";
    public static final String URL_PAYLOAD = ROOT_URL + "payloads/create";
    public static final String URL_PAYLOADS = ROOT_URL + "payloads";
    public static final String URL_USERS = ROOT_URL + "users";
    public static final String URL_TESTS = ROOT_URL + "test";
    public static final String URL_STATS_DAILYDATA = ROOT_URL + "stats/dailyData";
    public static final String URL_STATS_AVERAGEDATA = ROOT_URL + "test";

    // Test Urls
    public static final String TEST_FARMS = TEST_URL + "farms.json";
    public static final String TEST_NODES = TEST_URL + "nodes.json";

}
