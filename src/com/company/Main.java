package com.company;

import java.util.Scanner;

public class Main {
    public static String alphabet_RU = "АаБбВвГгДдЕеЁёЖжЗзИиЙйКкЛлМмНнОоПпРрСсТтУуФфХхЦцЧчШшЩщЪъЫыЬьЭэЮюЯя";
    public static String alphabet_EN = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz";

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите язык (EN или RU): ");
        String lang = scanner.next();
        System.out.println("Введите слово-ключ для шифрования: ");
        String key = scanner.next();
        int[] shift_array = keyword_numbers(key, lang);

        System.out.println("Введите текст, который хотите зашифровать: ");
        String text;
        text = scanner.nextLine(); //работает неверно, тк в начале строки у нас есть знак
        //см. https://qna.habr.com/q/269359
        text = scanner.nextLine(); //работает верно

        System.out.println(vigenere(shift_array, text, lang));

    }

    public static int[] keyword_numbers (String key, String lang) {
        int[] array = new int[key.length()];
        if (lang.equals("RU")) {
            for (int i = 0; i < key.length(); i++) {
                array[i] = alphabet_RU.indexOf(key.charAt(i)) +1;
            }
        }
        else {
            for (int i = 0; i < key.length(); i++) {
                array[i] = alphabet_EN.indexOf(key.charAt(i)) + 1;
            }
        }
        return array;
    }
    
    public static String vigenere(int[] shift_array, String text, String lang) {

        //определение используемого языка
        //можно заменить на case и тогда языков может быть больше
        String alphabet;
        if (lang.equals("RU")) {
            alphabet = alphabet_RU;
        }
        else {
            alphabet = alphabet_EN;
        }
        
        int i = 0; //
        int number;
        int a = 0; //счетчик сдвижения для ключевого слова
        int shift;
        String result = "";
        char letter;
        
        while (i<text.length()) {
            letter = text.charAt(i);
                number = alphabet.indexOf(letter); //номер буквы в алфавите
                shift = number + shift_array[a]; //итоговый номер буквы в алфавите

                //ограничители значения на случай ошибок
                while (shift > alphabet.length()) {
                    shift = shift - alphabet.length();
                }
                while (shift < 0) {
                    shift = shift + alphabet.length();
                }

                result += alphabet.charAt(shift);
                i++;
                a++;

                if (a >= shift_array.length) {
                    a = 0;
                }
        }
        return result;
    }
}


