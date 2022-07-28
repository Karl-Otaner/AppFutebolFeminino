package com.dio.soccernews;

import android.app.Application;

/**
 * FIXME Centralizamos uma instância de contexto em nosso {@link App} (mesmo sendo um "anti-pattern")
 * para extrairmos o maxímo dos nossos ViewModels e camada de acesso a dados. Entretando apesar
 * das nossas camadas ficarem mas coesas, o ideal seria usar uma solução de injeção de dependências
 * (como o Dagger ou Hilt).
 *
 * @see <a herf="https://stackoverflow.com/a/14857777/3072570">Android Singleton with Global Context</a>
 */

public class App extends Application {
    private static App instance;

    public static App getInstance() { return instance; }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }


}
