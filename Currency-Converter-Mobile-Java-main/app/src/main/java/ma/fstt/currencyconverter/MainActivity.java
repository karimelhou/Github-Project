package ma.fstt.currencyconverter;


import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText editTextAmount;
    private Spinner spinnerCurrencies;
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextAmount = findViewById(R.id.editTextAmount);
        spinnerCurrencies = findViewById(R.id.spinnerCurrencies);
        textViewResult = findViewById(R.id.textViewResult);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.currencies_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCurrencies.setAdapter(adapter);
    }

    public void convertCurrency(View view) {
        String amountStr = editTextAmount.getText().toString();
        if (!amountStr.isEmpty()) {
            double amount = Double.parseDouble(amountStr);
            double result = convert(amount, spinnerCurrencies.getSelectedItem().toString());
            textViewResult.setText(String.format("%.2f", result));
        }
    }

    private double convert(double amount, String currency) {
        double rate = getRate(currency);
        return amount * rate;
    }

    private double getRate(String currency) {
        switch (currency) {
            case "USD":
                return 9.96;
            case "GBP":
                return 12.70;
            case "EUR":
                return 10.9024;
            case "JPY":
                return 0.0687;
            case "CAD":
                return 7.43;
            default:
                return 1.0;
        }
    }

}
