package pl.nauka.jarek.calculator;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Scanner;

import pl.nauka.jarek.calculator.calculatorAlgorytm.ConvertToONP;
import pl.nauka.jarek.calculator.calculatorAlgorytm.ONPCalculation;

public class MainActivity extends AppCompatActivity {

    private TextView resultView;
    private TextView resultView2;
    private TextView resultView3;
    private Button cButton;
    private Button oButton;
    private Button divisionButton;
    private Button number7Button;
    private Button number8Button;
    private Button number9Button;
    private Button multiplicationButton;
    private Button number4Button;
    private Button number5Button;
    private Button number6Button;
    private Button minusButton;
    private Button number1Button;
    private Button number2Button;
    private Button number3Button;
    private Button plusButton;
    private Button numberPButton;
    private Button number0Button;
    private Button backspaceButton;
    private Button equalButton;

    private String equation ;
    private char[] equationArray;
    private String colorText;
    private int change;     //Kontrola poprawnego dodania znaku ','

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        resultView = findViewById(R.id.result_view);
        resultView2 = findViewById(R.id.result_view2);
        resultView3 = findViewById(R.id.result_view3);

        cButton = findViewById(R.id.C_buttom);
        oButton = findViewById(R.id.O_buttom);
        divisionButton = findViewById(R.id.division_buttom);
        number7Button = findViewById(R.id.numer_7_buttom);
        number8Button = findViewById(R.id.numer_8_buttom);
        number9Button = findViewById(R.id.numer_9_buttom);
        multiplicationButton = findViewById(R.id.multiplication_buttom);
        number4Button = findViewById(R.id.numer_4_buttom);
        number5Button = findViewById(R.id.numer_5_buttom);
        number6Button = findViewById(R.id.numer_6_buttom);
        minusButton = findViewById(R.id.minus_buttom);
        number1Button = findViewById(R.id.numer_1_buttom);
        number2Button = findViewById(R.id.numer_2_buttom);
        number3Button = findViewById(R.id.numer_3_buttom);
        plusButton = findViewById(R.id.plus_buttom);
        numberPButton = findViewById(R.id.numer_p_buttom);
        number0Button = findViewById(R.id.numer_0_buttom);
        backspaceButton = findViewById(R.id.backspace_buttom);
        equalButton = findViewById(R.id.equal_buttom);

        number7Button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                appendNumber("7");
            }
        });

        number8Button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                appendNumber("8");
            }
        });

        number9Button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                appendNumber("9");
            }
        });

        number4Button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                appendNumber("4");
            }
        });

        number5Button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                appendNumber("5");
            }
        });

        number6Button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                appendNumber("6");
            }
        });

        number1Button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                appendNumber("1");
            }
        });

        number2Button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                appendNumber("2");
            }
        });

        number3Button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                appendNumber("3");
            }
        });

        cButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                change = 0;
                resultView.setText(null);
                resultView2.setText(null);
                resultView3.setText(null);
            }
        });

        divisionButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                appendDivisionMultiplication("÷");
            }
        });

        multiplicationButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                appendDivisionMultiplication("x");
            }
        });

        minusButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                appendMinusPlus("-");
            }
        });

        plusButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                appendMinusPlus("+");
            }
        });

        number0Button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                equationArray = getEquation().toCharArray();

                if (getEquation().isEmpty())
                {
                    resultView.append("0");
                }
                else if (equationArray[0] != '0')
                {
                    resultView.append("0");
                }
            }
        });

        numberPButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (!getEquation().isEmpty()) {
                    if (getEquationLastChar() == '÷' || getEquationLastChar() == 'x' || getEquationLastChar() == '-' || getEquationLastChar() == '+' || getEquationLastChar() == '.') {
                        resultView.append("");
                    } else if (change != 1)
                    {
                        resultView.append(".");
                        change = 1;
                    }
                }
            }
        });

        backspaceButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                change = 0;
                backspace();
            }
        });

        equalButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                try {
                    if (!getEquation().isEmpty())
                    {
                        /**
                         * ConvertToONP
                         */
//                        resultView3.setText(String.valueOf(ConvertToONP.getEquationInONE(getEquation())));
                        String[] input = (ConvertToONP.getEquationInONE(getEquation())).split(" ");

                        String[] output = ConvertToONP.infixToRPN(input);
                        StringBuilder sb = new StringBuilder();

                        for (String token : output) {
                            sb.append(token + " ");
                        }
                        resultView3.setText(String.valueOf(sb));


                        /**
                         * ONPCalculation
                         */
                        Scanner sc = new Scanner(String.valueOf(sb));
                        while(sc.hasNext()) {
                            String line = sc.nextLine();
                            if (String.valueOf(ONPCalculation.eval(line.split(" "))) == "Infinity" || String.valueOf(ONPCalculation.eval(line.split(" "))) == "-Infinity")
                            {
                                Snackbar.make(v,"Dzielenie przez zero", Snackbar.LENGTH_SHORT).show();
                            }else
                                {
                                    resultView2.setText(String.valueOf(ONPCalculation.eval(line.split(" "))));
                                }
                        }
                    }

                }catch (RuntimeException exc)
                {
                    Snackbar.make(v,"Wprowadź poprawne równanie", Snackbar.LENGTH_SHORT).show();
                }
            }
        });

        oButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (getEquation().isEmpty())
                {
                    resultView.append("(");
                }
                else if (checkParenthesis() == "(")
                {
                    resultView.append(")");
                }
                else if(checkParenthesis() == ")")
                {
                    resultView.append("(");
                }
                else
                    {
                        resultView.append("(");
                    }
            }
        });

}
    /**
     * Sprawdza jaki nawias został wcześniej użyty w równaniu (metoda getEquation())
     */
    private String checkParenthesis()
    {
        String parenthesis = null;
        char [] chararray = getEquation().toCharArray();

        for (int i = 0; i < chararray.length; i++)
        {
            if (chararray[i] == '(')
            {
                parenthesis = "(";
            }
            else if (chararray[i] == ')')
            {
                parenthesis = ")";
            }
        }
        return parenthesis;
    }

    /**
     * Dodaje odpowiednio "÷" i "x" do Textview
     */
    private void appendDivisionMultiplication(String s) {
        if (!getEquation().isEmpty()) {
            change = 0;
            if (getEquationLastChar() == '÷' || getEquationLastChar() == 'x' || getEquationLastChar() == '-' || getEquationLastChar() == '+' || getEquationLastChar() == '.') {
                backspace();
                colorText = getColoredSpanned(s, Integer.toString(getResources().getColor(R.color.colorAccent)));
                resultView.append(Html.fromHtml(colorText));
            } else {
                colorText = getColoredSpanned(s, Integer.toString(getResources().getColor(R.color.colorAccent)));
                resultView.append(Html.fromHtml(colorText));
            }
        }
    }

    /**
     * Dodaje odpowiednio "-" i "+" do Textview
     */
    private void appendMinusPlus(String s) {
        change = 0;
        if (getEquation().isEmpty()) {
            colorText = getColoredSpanned(s, Integer.toString(getResources().getColor(R.color.colorAccent)));
            resultView.append(Html.fromHtml(colorText));
        } else if (getEquationLastChar() == '÷' || getEquationLastChar() == 'x' || getEquationLastChar() == '-' || getEquationLastChar() == '+'|| getEquationLastChar() == '.')
            //TODO Brak możliwości dodania np 22x33x(-3) dodać możliwość zmiany znaku za ()
        {
            backspace();
            colorText = getColoredSpanned(s, Integer.toString(getResources().getColor(R.color.colorAccent)));
            resultView.append(Html.fromHtml(colorText));
        } else {
            colorText = getColoredSpanned(s, Integer.toString(getResources().getColor(R.color.colorAccent)));
            resultView.append(Html.fromHtml(colorText));
        }
    }

    /**
     * Dodaje liczbe do Textview
     */
    private void appendNumber(String s) {
        if (getEquation().isEmpty() || getEquation().length() > 0) {
            resultView.append(s);
        }

        equationArray = getEquation().toCharArray();
        if (equationArray[0] == '0') {
            resultView.setText(null);
            resultView.append(s);
        }
    }

    /**
     * Kasuje jeden znak z Textview
     */
    private void backspace() {

        equationArray = getEquation().toCharArray();
        resultView.setText(null);

        for (int i = 0; i < equationArray.length-1; i++ )
        {
            if (equationArray[i] == '÷' || equationArray[i] == 'x'|| equationArray[i] == '-'|| equationArray[i] == '+')
            {
                colorText = getColoredSpanned(String.valueOf(equationArray[i]), Integer.toString(getResources().getColor(R.color.colorAccent)));
                resultView.append(Html.fromHtml(colorText));
            }
            else
                {
                    colorText = getColoredSpanned(String.valueOf(equationArray[i]), Integer.toString(getResources().getColor(R.color.black)));
                    resultView.append(Html.fromHtml(colorText));
                }
        }
    }

    /**
     * Zwraca równanie equation
     */

    private String getEquation()
    {
        equation = resultView.getText().toString();
        return equation;
    }

    /**
     * Zwraca ostatni znak (char) z równania (equation)
     */

    private char getEquationLastChar() {
        equationArray = getEquation().toCharArray();
        return equationArray[getEquation().length()-1];
    }

    /**
     * Zmiania kolor znaku działania "÷,x,-,+"
     */

    private String getColoredSpanned(String text, String color) {
        String input = "<font color=" + color + ">" + text + "</font>";
        return input;
    }

    private String[] stringArray(String input)
    {
        String [] result = new String[input.length()];

        for(int i = 0; i < input.length() ; i ++ )
        {
            result[i] = input.substring(i,i+1);
        }return result;
    }


}
