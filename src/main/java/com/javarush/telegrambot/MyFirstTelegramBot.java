package com.javarush.telegrambot;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.Map;

import static com.javarush.telegrambot.TelegramBotContent.*;

public class MyFirstTelegramBot extends MultiSessionTelegramBot {
    public static final String NAME = "jru_my_game_bot";
    public static final String TOKEN = "6467258970:AAHAWSbIjv1HtRidQ9Bl5j1q7ttL8sQZaUQ";
    private static final String STEP_2_BTN = "step_2_btn";
    private static final String STEP_2_BTN1 = STEP_2_BTN;
    private static final String STEP_1_BTN = "step_1_btn";
    private static final String STEP_3_BTN = "step_3_btn";
    private static final String STEP_4_BTN = "step_4_btn";
    private static final String STEP_5_BTN = "step_5_btn";
    private static final String STEP_6_BTN = "step_6_btn";
    private static final String STEP_7_BTN = "step_7_btn";
    private static final String STEP_8_BTN = "step_8_btn";

    public MyFirstTelegramBot() {
        super(NAME, TOKEN);
    }

    @Override
    public void onUpdateEventReceived(Update updateEvent) {
        //отобразим сообщение о начале игры - нужно взломать холодильник
        int glory = getUserGlory();
        if (getMessageText().equals("/glory")) {
            sendTextMessageAsync(String.valueOf(glory));
        }

        if (getMessageText().equals("/start")) {
            setUserGlory(0);
            sendPhotoMessageAsync("step_1_pic");
            sendTextMessageAsync(STEP_1_TEXT, Map.of("Взлом холодильника", STEP_1_BTN));
        }

        if (getCallbackQueryButtonKey().equals(STEP_1_BTN) && glory < 20) {
            addUserGlory(20);
            sendPhotoMessageAsync("step_2_pic");
            sendTextMessageAsync(STEP_2_TEXT, Map.of("Взять сосиску! +20 славы", STEP_2_BTN1,
                    "Взять рыбку! +20 славы", STEP_2_BTN1,
                    "Скинуть банку с огурцами! +20 славы", STEP_2_BTN1));
        }

        //взламываем робот - пылесос
        if (getCallbackQueryButtonKey().equals(STEP_2_BTN1) && glory < 40) {
            addUserGlory(20);
            sendPhotoMessageAsync("step_3_pic");
            sendTextMessageAsync(STEP_3_TEXT, Map.of("Взлом робота - пылесоса", STEP_3_BTN));
        }

        if (getCallbackQueryButtonKey().equals(STEP_3_BTN) && glory < 70) {
            addUserGlory(30);
            sendPhotoMessageAsync("step_4_pic");
            sendTextMessageAsync(STEP_4_TEXT, Map.of("Отправить робот за едой! +30 славы", STEP_4_BTN,
                    "Покататься верхом! +30 славы", STEP_4_BTN,
                    "Убежать от робота-пылесоса! +30 славы", STEP_4_BTN));
        }

        //взламываем камеру Go-Pro
        if (getCallbackQueryButtonKey().equals(STEP_4_BTN) && glory < 100) {
            addUserGlory(30);
            sendPhotoMessageAsync("step_5_pic");
            sendTextMessageAsync(STEP_5_TEXT, Map.of("Надеть и включить GoPro!", STEP_5_BTN));
        }

        if (getCallbackQueryButtonKey().equals(STEP_5_BTN) && glory < 140) {
            addUserGlory(40);
            sendPhotoMessageAsync("step_6_pic");
            sendTextMessageAsync(STEP_6_TEXT, Map.of("Бегать по крышам, снимать на GoPro! +40 славы", STEP_6_BTN,
                    "С GoPro нападать на других котов из засады! +40 славы", STEP_6_BTN,
                    "С GoPro нападать на собак из засады! +40 славы", STEP_6_BTN));
        }

        //взламываем компьютер
        if (getCallbackQueryButtonKey().equals(STEP_6_BTN) && glory < 180) {
            addUserGlory(40);
            sendPhotoMessageAsync("step_7_pic");
            sendTextMessageAsync(STEP_7_TEXT, Map.of("Взлом пароля", STEP_7_BTN));
        }

        if (getCallbackQueryButtonKey().equals(STEP_7_BTN) && glory < 230) {
            addUserGlory(50);
            sendPhotoMessageAsync("step_8_pic");
            sendTextMessageAsync(STEP_8_TEXT, Map.of("Выйти во двор", STEP_8_BTN));
        }

        //хвастаемся дворовым котам
        if (getCallbackQueryButtonKey().equals(STEP_8_BTN) && glory == 230) {
            sendImageMessageAsync("D:\\java\\final_pic.jpg");
            sendTextMessageAsync(FINAL_TEXT);
        }
    }

    public static void main(String[] args) throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(new MyFirstTelegramBot());
    }
}
