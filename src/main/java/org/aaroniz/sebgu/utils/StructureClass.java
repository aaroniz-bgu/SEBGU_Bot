package org.aaroniz.sebgu.utils;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.exceptions.InvalidTokenException;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class StructureClass extends ListenerAdapter {

    /* WHOIS <string> - Who is that
     * WHOIS ADD <string> <description> - add this name with this description
     * PANIC - replies with the 3 most urgent assignments
     * PANIC ADD <string>, <description>, <datetime> - adding hw to panic
     * WHATIS <string> - Short line of what's <string> is
     * STACKOVERFLOW <string> - searches for <string> in stackoverflow
     * STACKEXCHANGE <string> - searches for <string> in stack exchange
     * G4G <string> - search G4G for that topic
     * YT <string> - search yt tutorial for that topic
     * EXAMS - show 5 closest exams
     * MOTD - random message of the day
     * LASTSEEN <user> - last message of user
     */

//    @Override
//    public void onMessageReceived(MessageReceivedEvent event){
//        if (event.getAuthor().isBot()) return; //Bot ignoring
//
//        String message = event.getMessage().getContentRaw();
//
//        if (/**/false)
//        {
//            /*Here send message back to channel*/
//        }
//    }

//    public void func(){
//        //what
//    }
//
//            try {
//        String token = Files.readString(Path.of("resources/token.txt"));
//        //Logging in the bot:
//        JDA jdaApi = JDABuilder.createDefault(token).build();
//        //Funny joke:
//        ((JDABuilder) jdaApi).setActivity(Activity.playing("Mivney Netunim"));
//    } catch(
//    IOException e){
//        System.out.println("Reading token.txt was interrupted.");
//    } catch(
//    InvalidTokenException e){
//        System.out.println("Illegal token, please insert the app token into token.txt");
//    } catch(Exception e) {
//        System.out.println("Unexpected error occurred.");
//    } finally {
//        //something idk.
//    }
}
