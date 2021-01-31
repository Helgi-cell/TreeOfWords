package treeofwords;
/**
 * @author Suhodolsky Oleg
 * Interface for store final int values
 * to create word signatures
 */
public interface SignatureInterface {

    final static int RUS = 7;
    final static int ENG = 4;
    final static int COEFENG = 1000;
    final static int COEFRUS = 2000;

    
  /* 
    String string[]
            = {"sam", "real", "connor", "thread", "integer", "low", "yellow", "red", "alpha",
               "Aha", "MotleyCrue", "Cinderella", "Zero", "Nazareth", "Doors", "metallica",
               "ddt", "queen","black", "joly", "roger", "sam","low", "kiss", "DeepPurple", "Java",
               "English", "FrankieGoesToHollywood", "Judas Priest", "AcDc", "judas priest", "aha",
                "Testament", "john", "mike", "Foreigner", "megadeath", "Metallica", "Master", 
                "BlackCoffee", "DDT", "alisa", "Accept", "Kreator", "LedZeppelin", "Motorhead",
                "aria", "KingDiamond", "kiss","ok", "adel","ret", "rat", "motleyCrue", "BadCompany",
                "IronMaiden", "Queen", "metalChurch", "Scorpions", "pantera","was", "wert","uber", "basta",
                "audiorama", "CreedensClearwaterRevival", "mike", "Mike", "mike", "mike", "mikey", "mikey",
                "sanny", "Sanny", "john", "John", "john", "zero", "acdc", "john", "aha", "ACDC", "acDc", "DDT",
                "mikey", "amha", "aga","assa","adda","asa", "queen","black", "joly", "roger", "sam","low", "kiss",
                "alpha", "Aha", "MotleyCrue", "Cinderella", "alpha", "Aha", "MotleyCrue", "Cinderella", "Zero",
                "Nazareth", "Nazareth", "Doors", "metallica", "ddt", "queen","black", "jolly", "roger", "sam",
                "low", "kiss", "aha", "ada","asa","ada","asa", "queen","black", "joly", "roger", "sam","low",
                "kiss", "david", "smile", "roth", "cold", "whispear","fan", "england", "germany", "usa",
                "stream", "for", "loser", "lie", "lang", "table", "anny", "alex", "ken", "wain", "wait",
                "low", "kiss", "aba", "ana","usa","ata","asa", "queen","black", "jony", "roger", "sam","low",
                "low", "kiss", "uha", "ada","asa","ada","aka", "queen","black", "jack", "roger", "sem","low",
                "IronMaiden", "Queen", "metalChurch", "Scorpions", "pantera","was", "wert","uber", "basta",
                "audiorama", "CreedensClearwaterRevival", "mike", "Mike", "mike", "mike", "mikey", "mikey",
                "sanny", "Sanny", "king", "John", "john", "zero", "acdc", "john", "aha", "ACDC", "Dici", "DDT",
                "mikey", "aha", "ada","asa","ada","asa", "queen","black", "joly", "roger", "sam","low", "kiss",
                "alpha", "Aha", "MotleyCrue", "Cinderella", "alpha", "Aha", "MotleyCrue", "Cinderella", "Zero",
                "Nazareth", "Nazareth", "Doors", "metallica", "ddt", "queen","black", "joly", "roger", "sam",
                "alla", "debi", "kris", "swan", "greg", "honey", "darling", "man", "woman", "xert", "itra", "gas",
                "volvo", "daf", "iveco", "kort", "cat", "dog", "hammer", "zara", "nova", "caddy", "special", "drake",
                "deel", "school", "shoes", "shirt", "nanny", "nina", "oleg", "norway", "skill", "slice", "north",
                "west", "wow", "double", "int", "float", "weather", "ok", "now", "not", "meal", "matter", "strike",
                "trouble", "invalid", "vert", "lama", "lawrence", "cruz", "zaba", "holly", "new", "year", "happy",
                "erlang", "tutorial", "specialize", "add", "dirk", "aux", "warming", "enduro", "moto", "auto", "coef",
                "just", "axe", "lance", "sword", "maxx", "milita", "fast", "lamp", "table", "girl", "super", "this",
                "treble", "oak", "quality", "custom", "comsume", "probe", "var", "let", "exersize", "mult", "plus", "reality",
                "jar", "nan", "exe", "pop", "push", "rail", "pole", "now", "war", "loft", "ball", "table", "kat", "coin",
                "zak", "xedos", "exodus", "monday", "class", "method", "park", "comp", "break", "return", "klava", "iton",
                "ssd", "sport", "void", "bass", "jira", "grant", "home", "nike", "jordan", "liss", "qwerty", "potha", "miha",
                "retrieval", "ld", "winston", "park", "chevy", "kitty" 
            
            };
*/
    
    
//     String string[]
//            = {"sam", "real", "connor", "thread", "integer", "low", "yellow", "red", "alpha",
//               "Aha", "MotleyCrue", "Cinderella", "Zero", "Nazareth", "Doors", "metallica",
//               "just", "axe", "lance", "sword", "maxx", "milita", "fast", "lamp", "table", "girl", "super", "this",
//                "treble", "oak", "quality", "custom", "comsume", "probe", "var", "let", "exersize", "mult", "plus",
//                "reality","farm", "exodus"
//               
//            };
 
    /**
     *  Testing data string and identification. Indexes arrays.
     * 
     * 
     */
    
     String string[]
            = {"sam", "real", "connor", "thread", "integer", "low", "yellow", "red", "alpha",
               "Aha", "MotleyCrue", "Cinderella", "Zero", "Nazareth", "Doors", "metallica",
               "zzzzzzzzzz", "zzzzzzzzzy", "aaaaaaaaa", "aaaaaaaab", "zerz", "sam", "real", "connor", "thread",
               "sem", "john", "sem"
            }; 
    
     Double identification [] = {2.0, 5.0, 45.0, 77.0, 56.0, 12.0, 89.0, 112.0,
                                 63.0, 11.0, 4.0, 69.0, 81.0, 87.0, 132.0, 99.0,
                                 201.0, 28.0, 42.0, 3.0, 10.0, 49.0, 93.0, 111.0, 113.0,
                                 48.0, 101.0, 47.0
                                };
    
}
