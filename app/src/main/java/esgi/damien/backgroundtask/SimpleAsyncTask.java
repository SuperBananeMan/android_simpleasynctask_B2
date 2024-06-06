package esgi.damien.backgroundtask;

import android.os.AsyncTask;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.Random;

public class SimpleAsyncTask extends AsyncTask<Void, Void, String> {
    private WeakReference<TextView> mTextView;

    SimpleAsyncTask(TextView tv) {
        mTextView = new WeakReference<>(tv);
    }

    @Override
    protected String doInBackground(Void... voids) {
        // Génère un nombre aléatoire entre 0 et 10
        Random r = new Random();
        int n = r.nextInt(11);

        // Temps de sommeil en millisecondes le temps de pivoter
        int s = n * 200;

        // Essayez de dormir pendant le temps spécifié
        try {
            Thread.sleep(s);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Renvoie un message de réveil
        return "Enfin réveillé après avoir dormi pendant " + s + " millisecondes !";
    }

    /**
     * Méthode appelée après la fin de l'exécution de {@link #doInBackground}.
     * Cette méthode est appelée sur le thread principal et reçoit le résultat de l'opération
     *
     * @param result The result of the operation computed by {@link #doInBackground}.
     */
    protected void onPostExecute(String result) {
        mTextView.get().setText(result);
    }
}
