//package dev.wp.craftoria_core.config;
//
//import dev.wp.craftoria_core.Craftoria;
//import net.neoforged.neoforge.common.ModConfigSpec;
//
//public class ServerConfig {
//    private static final ModConfigSpec.Builder BUILDER;
//    private static final ModConfigSpec.BooleanValue SUPPRESS_UNKNOWN_PACKET_ID;
//    public static final ModConfigSpec SPEC;
//
//    private static String key(String path) {
//        return Craftoria.ID + ".config." + path;
//    }
//
//    static {
//        BUILDER = new ModConfigSpec.Builder();
//        {
//            BUILDER.translation(key("mixin_toggles")).push("Mixin Toggles");
//            SUPPRESS_UNKNOWN_PACKET_ID = BUILDER
//                    .translation(key("suppress_unknown_packet_id"))
//                    .comment("Suppress the 'Unknown packet ID -1' error in the logs.")
//                    .define("suppress_unknown_packet_id", false);
//            BUILDER.pop();
//        }
//        SPEC = BUILDER.build();
//    }
//
//    public static boolean suppressUnknownPacketId;
//
//    public static void init() {
//        suppressUnknownPacketId = SUPPRESS_UNKNOWN_PACKET_ID.get();
//    }
//}
