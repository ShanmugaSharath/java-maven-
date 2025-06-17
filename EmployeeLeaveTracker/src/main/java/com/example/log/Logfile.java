package com.example.log;
import java.util.*;
import java.util.logging.*;

 
public class Logfile {
    private static final Logger lg = Logger.getLogger(Logfile.class.getName());

    static {
        try {
            LogManager.getLogManager().reset(); // Clear default handlers

            lg.setLevel(Level.INFO); // Use only basic levels

            ConsoleHandler ch = new ConsoleHandler();
            ch.setLevel(Level.INFO);
            lg.addHandler(ch);

            FileHandler fh = new FileHandler("C:\\Users\\shanmugasharath.a\\Desktop\\leave-application.log");
            fh.setLevel(Level.INFO);
            fh.setFormatter(new SimpleFormatter());
            lg.addHandler(fh);
            //System.out.println("Log file path: " + System.getProperty("user.dir"));

        } catch (Exception e) {
            lg.log(Level.SEVERE, "Failed to set up logger", e);
        }
    }
    
    public static Logger getlg() {
        return lg;
        
    }
}
