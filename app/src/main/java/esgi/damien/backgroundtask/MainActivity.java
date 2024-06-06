package esgi.damien.backgroundtask;

import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private TextView mTextView;

    private static final String TEXT_STATE = "currentText";

    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = findViewById(R.id.textView1);
        mProgressBar = findViewById(R.id.progressBar);

        // Vérifie si l'état a été enregistré
        if(savedInstanceState!=null){
            mTextView.setText(savedInstanceState.getString(TEXT_STATE));
        }
    }

    /**
     * Méthode appelée lorsque le bouton est cliqué.
     * La méthode utilise l'AsyncTask pour faire un travail en arrière-plan.
     *
     * @param view
     */
    public void startTask(View view) {
        // Placer un message dans la vue texte
        mTextView.setText(R.string.napping);

        // Montre une barre de progression et change pour indiquer que le travail est en cours
        mProgressBar.setVisibility(View.VISIBLE);

        // Démarre l'AsyncTask.
        new SimpleAsyncTask(mTextView).execute();
    }

    /**
     * Méthode appelée lorsque l'activité est en pause.
     * Enregistre le contenu de TextView.
     *
     * @param outState Bundle in which to place your saved state.
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Enregistre l'état de TextView
        outState.putString(TEXT_STATE, mTextView.getText().toString());
    }
}