package moe.tristan.harudex.model.common.statics;

import static java.util.Arrays.stream;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

import java.util.Map;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import moe.tristan.harudex.model.common.CodedEnumeration;

public enum Tag implements CodedEnumeration {

    _4_KOMA("b11fda93-8f1d-4bef-b2ed-8803d3733170"),
    ACTION("391b0423-d847-456f-aff0-8b0cfc03066b"),
    ADAPTATION("f4122d1c-3b44-44d0-9936-ff7502c39ad3"),
    ADVENTURE("87cc87cd-a395-47af-b27a-93258283bbc6"),
    ALIENS("e64f6742-c834-471d-8d72-dd51fc02b835"),
    ANIMALS("3de8c75d-8ee3-48ff-98ee-e20a65c86451"),
    ANTHOLOGY("51d83883-4103-437c-b4b1-731cb73d786c"),
    AWARD_WINNING("0a39b5a1-b235-4886-a747-1d05d216532d"),
    BOYS_LOVE("5920b825-4181-4a17-beeb-9918b0ff7a30"),
    COMEDY("4d32cc48-9f00-4cca-9b5a-a839f0764984"),
    COOKING("ea2bc92d-1c26-4930-9b7c-d5c0dc1b6869"),
    CRIME("5ca48985-9a9d-4bd8-be29-80dc0303db72"),
    CROSSDRESSING("9ab53f92-3eed-4e9b-903a-917c86035ee3"),
    DELINQUENTS("da2d50ca-3018-4cc0-ac7a-6b7d472a29ea"),
    DEMONS("39730448-9a5f-48a2-85b0-a70db87b1233"),
    DOUJINSHI("b13b2a48-c720-44a9-9c77-39c9979373fb"),
    DRAMA("b9af3a63-f058-46de-a9a0-e0c13906197a"),
    FAN_COLORED("7b2ce280-79ef-4c09-9b58-12b7c23a9b78"),
    FANTASY("cdc58593-87dd-415e-bbc0-2ec27bf404cc"),
    FULL_COLOR("f5ba408b-0e7a-484d-8d49-4e9125ac96de"),
    GENDERSWAP("2bd2e8d0-f146-434a-9b51-fc9ff2c5fe6a"),
    GHOSTS("3bb26d85-09d5-4d2e-880c-c34b974339e9"),
    GIRLS_LOVE("a3c67850-4684-404e-9b7f-c69850ee5da6"),
    GORE("b29d6a3d-1569-4e7a-8caf-7557bc92cd5d"),
    GYARU("fad12b5e-68ba-460e-b933-9ae8318f5b65"),
    HAREM("aafb99c1-7f60-43fa-b75f-fc9502ce29c7"),
    HISTORICAL("33771934-028e-4cb3-8744-691e866a923e"),
    HORROR("cdad7e68-1419-41dd-bdce-27753074a640"),
    INCEST("5bd0e105-4481-44ca-b6e7-7544da56b1a3"),
    ISEKAI("ace04997-f6bd-436e-b261-779182193d3d"),
    LOLI("2d1f5d56-a1e5-4d0d-a961-2193588b08ec"),
    LONG_STRIP("3e2b8dae-350e-4ab8-a8ce-016e844b9f0d"),
    MAFIA("85daba54-a71c-4554-8a28-9901a8b0afad"),
    MAGIC("a1f53773-c69a-4ce5-8cab-fffcd90b1565"),
    MAGICAL_GIRLS("81c836c9-914a-4eca-981a-560dad663e73"),
    MARTIAL_ARTS("799c202e-7daa-44eb-9cf7-8a3c0441531e"),
    MECHA("50880a9d-5440-4732-9afb-8f457127e836"),
    MEDICAL("c8cbe35b-1b2b-4a3f-9c37-db84c4514856"),
    MILITARY("ac72833b-c4e9-4878-b9db-6c8a4a99444a"),
    MONSTER_GIRLS("dd1f77c5-dea9-4e2b-97ae-224af09caf99"),
    MONSTERS("36fd93ea-e8b8-445e-b836-358f02b3d33d"),
    MUSIC("f42fbf9e-188a-447b-9fdc-f19dc1e4d685"),
    MYSTERY("ee968100-4191-4968-93d3-f82d72be7e46"),
    NINJA("489dd859-9b61-4c37-af75-5b18e88daafc"),
    OFFICE_WORKERS("92d6d951-ca5e-429c-ac78-451071cbf064"),
    OFFICIAL_COLORED("320831a8-4026-470b-94f6-8353740e6f04"),
    ONESHOT("0234a31e-a729-4e28-9d6a-3f87c4966b9e"),
    PHILOSOPHICAL("b1e97889-25b4-4258-b28b-cd7f4d28ea9b"),
    POLICE("df33b754-73a3-4c54-80e6-1a74a8058539"),
    POST_APOCALYPTIC("9467335a-1b83-4497-9231-765337a00b96"),
    PSYCHOLOGICAL("3b60b75c-a2d7-4860-ab56-05f391bb889c"),
    REINCARNATION("0bc90acb-ccc1-44ca-a34a-b9f3a73259d0"),
    REVERSE_HAREM("65761a2a-415e-47f3-bef2-a9dababba7a6"),
    ROMANCE("423e2eae-a7a2-4a8b-ac03-a8351462d71d"),
    SAMURAI("81183756-1453-4c81-aa9e-f6e1b63be016"),
    SCHOOL_LIFE("caaa44eb-cd40-4177-b930-79d3ef2afe87"),
    SCI_FI("256c8bd9-4904-4360-bf4f-508a76d67183"),
    SEXUAL_VIOLENCE("97893a4c-12af-4dac-b6be-0dffb353568e"),
    SHOTA("ddefd648-5140-4e5f-ba18-4eca4071d19b"),
    SLICE_OF_LIFE("e5301a23-ebd9-49dd-a0cb-2add944c7fe9"),
    SPORTS("69964a64-2f90-4d33-beeb-f3ed2875eb4c"),
    SUPERHERO("7064a261-a137-4d3a-8848-2d385de3a99c"),
    SUPERNATURAL("eabc5b4c-6aff-42f3-b657-3e90cbd00b75"),
    SURVIVAL("5fff9cde-849c-4d78-aab0-0d52b2ee1d25"),
    THRILLER("07251805-a27e-4d59-b488-f0bfbec15168"),
    TIME_TRAVEL("292e862b-2d17-4062-90a2-0356caa4ae27"),
    TRADITIONAL_GAMES("31932a7e-5b8e-49a6-9f12-2afa39dc544c"),
    TRAGEDY("f8f62932-27da-4fe4-8ee1-6779a8c5edba"),
    USER_CREATED("891cf039-b895-47f0-9229-bef4c96eccd4"),
    VAMPIRES("d7d1730f-6eb0-4ba6-9437-602cac38664c"),
    VIDEO_GAMES("9438db5a-7e2a-4ac0-b39e-e0d95a34b8a8"),
    VILLAINESS("d14322ac-4d6f-4e9b-afd9-629d5f4d8a41"),
    VIRTUAL_REALITY("8c86611e-fab7-4986-9dec-d1a2f44acdd5"),
    WEB_COMIC("e197df38-d0e7-43b5-9b09-2842d0c326dd"),
    WUXIA("acc803a4-c95a-4c22-86fc-eb6b582d82a2"),
    ZOMBIES("631ef465-9aba-4afb-b0fc-ea10efe274a8");

    private static final Map<UUID, Tag> REVERSE_LOOKUP = stream(values()).collect(toMap(Tag::getId, identity()));

    private final UUID id;

    Tag(String id) {
        this.id = UUID.fromString(id);
    }

    public UUID getId() {
        return id;
    }

    @Override
    public String getCode() {
        return id.toString();
    }

    @JsonValue
    public String toJson() {
        return getCode();
    }

    @JsonCreator
    public Tag fromJson(String value) {
        return REVERSE_LOOKUP.get(UUID.fromString(value));
    }

}
