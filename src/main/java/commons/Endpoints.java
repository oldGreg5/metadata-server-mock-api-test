package commons;

public interface Endpoints {
    String BASE = "http://metadata-server-mock.herokuapp.com/metadata/";
    String BASE_HAPPY = BASE + "919e8a1922aaa764b1d66407c6f62244e77081215f385b60a62091494861707079436f696e";
    String BASE_AMAZING = BASE + "2048c7e09308f9138cef8f1a81733b72e601d016eea5eef759ff2933416d617a696e67436f696e";
    String QUERY = BASE + "query";
    String PROPS = "properties/";
    String PROPS_SUBJECT = PROPS + "subject";
    String PROPS_URL = PROPS + "url";
    String PROPS_NAME = PROPS + "name";
    String PROPS_TICKER = PROPS + "ticker";
    String PROPS_DECIMALS = PROPS + "decimals";
    String PROPS_POLICY = PROPS + "policy";
    String PROPS_LOGO = PROPS + "logo";
    String PROPS_DESCRIPTION = PROPS + "description";
}
