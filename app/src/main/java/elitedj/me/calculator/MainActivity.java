package elitedj.me.calculator;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.math.BigDecimal;
import java.util.Stack;

/**
 * This App named Calculator.
 * It implements the basic four operations. Add, subtract, multiply and divide.
 * I have tested a lot of operations and it seems to be reliable.
 *
 *
 * @author Elitedj
 *
 * @param isInit Whether the expression is in an initialization state
 * @param lastIsOper Whether the last input is operator like add, subtract, multiply, divide
 * @param haveDot A boolean value in order to judge whether the number have a decimal point
 * @param haveNonzeroNum A boolean value in order to judge whether the number have nonzero number
 * @param haveZero A boolean value in order to judge whether the number have zero
 * @param text The expression
 * @param isInitStack In order to rollback isInit
 * @param lastIsOperStack In order to lastIsOper
 * @param haveDotStack In order to rollback haveDot
 * @param haveNonzeroNumStack In order to rollback haveNonzeroNum
 * @param haveZeroStack In order to rollback haveZero
 *
 * @version 2.0
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText editText;
    private Button zero;
    private Button one;
    private Button two;
    private Button three;
    private Button four;
    private Button five;
    private Button six;
    private Button seven;
    private Button eight;
    private Button nine;
    private Button dot;
    private Button clear;
    private Button equal;
    private Button add;
    private Button subtract;
    private Button multiply;
    private Button divide;
    private ImageButton back;

    private boolean isInit;
    private boolean lastIsOper;
    private boolean haveDot;
    private boolean haveNonzeroNum;
    private boolean haveZero;

    private StringBuffer text;

    Stack<Boolean> isInitStack;
    Stack<Boolean> lastIsOperStack;
    Stack<Boolean> haveDotStack;
    Stack<Boolean> haveNonzeroNumStack;
    Stack<Boolean> haveZeroStack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Make the system status bar transparent
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }

        // initialization
        init();

    }

    /**
     * Initialization
     */
    private void init()
    {
        editText = findViewById(R.id.edittext);
        zero = findViewById(R.id.zero);
        one = findViewById(R.id.one);
        two = findViewById(R.id.two);
        three = findViewById(R.id.three);
        four = findViewById(R.id.four);
        five = findViewById(R.id.five);
        six = findViewById(R.id.six);
        seven = findViewById(R.id.seven);
        eight = findViewById(R.id.eight);
        nine = findViewById(R.id.nine);
        dot = findViewById(R.id.dot);
        clear = findViewById(R.id.clear);
        equal = findViewById(R.id.equal);
        add = findViewById(R.id.add);
        subtract = findViewById(R.id.subtract);
        multiply = findViewById(R.id.multiply);
        divide = findViewById(R.id.divide);
        back = findViewById(R.id.back);

        editText.setOnClickListener(this);
        zero.setOnClickListener(this);
        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
        four.setOnClickListener(this);
        five.setOnClickListener(this);
        six.setOnClickListener(this);
        seven.setOnClickListener(this);
        eight.setOnClickListener(this);
        nine.setOnClickListener(this);
        dot.setOnClickListener(this);
        clear.setOnClickListener(this);
        equal.setOnClickListener(this);
        add.setOnClickListener(this);
        subtract.setOnClickListener(this);
        multiply.setOnClickListener(this);
        divide.setOnClickListener(this);
        back.setOnClickListener(this);

        isInit = true;
        lastIsOper = true;
        haveDot =false;
        haveNonzeroNum = false;
        haveZero = false;
        text = new StringBuffer("");
        editText.setText(text.toString());

        isInitStack = new Stack<>();
        lastIsOperStack = new Stack<>();
        haveDotStack = new Stack<>();
        haveNonzeroNumStack = new Stack<>();
        haveZeroStack = new Stack<>();

    }

    /**
     * Clear all stack
     */
    private void clearStack(){
        isInitStack.clear();
        lastIsOperStack.clear();
        haveDotStack.clear();
        haveZeroStack.clear();
        haveNonzeroNumStack.clear();
    }

    /**
     * Push value to their stack
     * When the number buttons or operator buttons was clicked, this function will be called
     */
    private void stackPush(){
        isInitStack.push(new Boolean(isInit));
        lastIsOperStack.push(new Boolean(lastIsOper));
        haveDotStack.push(new Boolean(haveDot));
        haveNonzeroNumStack.push(new Boolean(haveNonzeroNum));
        haveZeroStack.push(new Boolean(haveZero));
    }

    /**
     * Rollback the value
     * When the back button clicked, this function will be called
     */
    private void stackPop(){
        if(isInitStack.empty() && lastIsOperStack.empty() && haveZeroStack.empty() && haveNonzeroNumStack.empty() && haveDotStack.empty())return;
        isInit = isInitStack.pop();
        lastIsOper = lastIsOperStack.pop();
        haveDot = haveDotStack.pop();
        haveNonzeroNum = haveNonzeroNumStack.pop();
        haveZero = haveZeroStack.pop();
    }

    /**
     * Set the edittext cursor always in the last
     */
    private void setEdittextSelection(){
        editText.setSelection(text.length());
    }

    @Override
    public void onClick(View v) {
        //Toast.makeText(this, ""+v.getId(), Toast.LENGTH_SHORT).show();
        switch (v.getId()){
            case R.id.zero:
                zeroFun();
                break;

            case R.id.one:
                oneFun();
                break;

            case R.id.two:
                twoFun();
                break;

            case R.id.three:
                threeFun();
                break;

            case R.id.four:
                fourFun();
                break;

            case R.id.five:
                fiveFun();
                break;

            case R.id.six:
                sixFun();
                break;

            case R.id.seven:
                sevenFun();
                break;

            case R.id.eight:
                eightFun();
                break;

            case R.id.nine:
                nineFun();
                break;

            case R.id.dot:
                dotFun();
                break;

            case R.id.add:
                addFun();
                break;

            case R.id.subtract:
                subtractFun();
                break;

            case R.id.multiply:
                multiplyFun();
                break;

            case R.id.divide:
                divideFun();
                break;

            case R.id.back:
                backFun();
                break;

            case R.id.clear:
                clearFun();
                break;

            case R.id.equal:
                equalFun();
                break;

                default: break;
        }
        setEdittextSelection();
    }

    /**
     * Zero button click function
     * Make sure no leading zero appear
     */
    private void zeroFun(){
        if(isInit){
            stackPush();
            text.append("0");
            editText.setText(text.toString());
            isInit = false;
            haveZero = true;
            lastIsOper = false;
        }
        if(lastIsOper || haveDot || haveNonzeroNum){
            stackPush();
            lastIsOper = false;
            haveZero = true;
            text.append('0');
            editText.setText(text.toString());
        }
    }

    /**
     * The one button was clicked
     */
    private void oneFun(){
        if(haveZero && !(haveNonzeroNum || haveDot))return;
        stackPush();
        if(isInit){
            text = new StringBuffer("1");
            isInit = false;
        }
        else{
            text.append('1');
        }
        haveNonzeroNum = true;
        lastIsOper = false;
        editText.setText(text.toString());
    }

    /**
     * The two button was clicked
     */
    private void twoFun(){
        if(haveZero && !(haveNonzeroNum || haveDot))return;
        stackPush();
        if(isInit){
            text = new StringBuffer("2");
            isInit = false;
        }
        else{
            text.append('2');
        }
        haveNonzeroNum = true;
        lastIsOper = false;
        editText.setText(text.toString());
    }

    /**
     * The three button was clicked
     */
    private void threeFun(){
        if(haveZero && !(haveNonzeroNum || haveDot))return;
        stackPush();
        if(isInit){
            text = new StringBuffer("3");
            isInit = false;
        }
        else{
            text.append('3');
        }
        haveNonzeroNum = true;
        lastIsOper = false;
        editText.setText(text.toString());
    }

    /**
     * The four button was clicked
     */
    private void fourFun(){
        if(haveZero && !(haveNonzeroNum || haveDot))return;
        stackPush();
        if(isInit){
            text = new StringBuffer("4");
            isInit = false;
        }
        else{
            text.append('4');
        }
        haveNonzeroNum = true;
        lastIsOper = false;
        editText.setText(text.toString());
    }

    /**
     * The five button was clicked
     */
    private void fiveFun(){
        if(haveZero && !(haveNonzeroNum || haveDot))return;
        stackPush();
        if(isInit){
            text = new StringBuffer("5");
            isInit = false;
        }
        else{
            text.append('5');
        }
        haveNonzeroNum = true;
        lastIsOper = false;
        editText.setText(text.toString());
    }

    /**
     * The six button was clicked
     */
    private void sixFun(){
        if(haveZero && !(haveNonzeroNum || haveDot))return;
        stackPush();
        if(isInit){
            text = new StringBuffer("6");
            isInit = false;
        }
        else{
            text.append('6');
        }
        haveNonzeroNum = true;
        lastIsOper = false;
        editText.setText(text.toString());
    }

    /**
     * The seven button was clicked
     */
    private void sevenFun(){
        if(haveZero && !(haveNonzeroNum || haveDot))return;
        stackPush();
        if(isInit){
            text = new StringBuffer("7");
            isInit = false;
        }
        else{
            text.append('7');
        }
        haveNonzeroNum = true;
        lastIsOper = false;
        editText.setText(text.toString());
    }

    /**
     * The eight button was clicked
     */
    private void eightFun(){
        if(haveZero && !(haveNonzeroNum || haveDot))return;
        stackPush();
        if(isInit){
            text = new StringBuffer("8");
            isInit = false;
        }
        else{
            text.append('8');
        }
        haveNonzeroNum = true;
        lastIsOper = false;
        editText.setText(text.toString());
    }

    /**
     * The nine button was clicked
     */
    private void nineFun(){
        if(haveZero && !(haveNonzeroNum || haveDot))return;
        stackPush();
        if(isInit){
            text = new StringBuffer("9");
            isInit = false;
        }
        else{
            text.append('9');
        }
        haveNonzeroNum = true;
        lastIsOper = false;
        editText.setText(text.toString());
    }

    /**
     * The clear button was clicked
     * Make initialization
     */
    private void clearFun(){
        text = new StringBuffer("");
        clearStack();
        isInit = true;
        lastIsOper = true;
        haveDot = false;
        haveNonzeroNum = false;
        haveZero = false;
        editText.setText(text.toString());
    }

    /**
     * When dot button was clicked
     * Make sure a number has only one decimal point
     */
    private void dotFun(){
        if(isInit || lastIsOper || haveDot)return;
        stackPush();
        haveDot = true;
        lastIsOper = true;
        text.append('.');
        editText.setText(text.toString());
    }

    /**
     * When back button was clicked
     * Rollback some value
     */
    private void backFun(){
        if(isInit)return;
        if(text.length()>1){
            text.deleteCharAt(text.length() - 1);
        }
        else{
            text = new StringBuffer("0");
        }
        stackPop();
        editText.setText(text.toString());
    }

    /**
     * When add button was clicked
     * Make sure there are no consecutive add signs
     */
    private void addFun(){
        if(isInit || lastIsOper)return;
        stackPush();
        text.append('+');
        lastIsOper = true;
        haveNonzeroNum = false;
        haveDot = false;
        haveZero = false;
        editText.setText(text.toString());
    }

    /**
     * When subtract button was clicked
     * Make sure there are no consecutive subtract signs
     */
    private void subtractFun(){
        if(isInit || lastIsOper)return;
        stackPush();
        text.append('-');
        lastIsOper = true;
        haveNonzeroNum = false;
        haveDot = false;
        haveZero = false;
        editText.setText(text.toString());
    }

    /**
     * When multiply button was clicked
     * Make sure there are no consecutive multiply signs
     */
    private void multiplyFun(){
        if(isInit || lastIsOper)return;
        stackPush();
        text.append('×');
        lastIsOper = true;
        haveNonzeroNum = false;
        haveDot = false;
        haveZero = false;
        editText.setText(text.toString());
    }

    /**
     * When divide button was clicked
     * Make sure there are no consecutive divide signs
     */
    private void divideFun(){
        if(isInit || lastIsOper)return;
        stackPush();
        text.append('÷');
        lastIsOper = true;
        haveNonzeroNum = false;
        haveDot = false;
        haveZero = false;
        editText.setText(text.toString());
    }

    /**
     * When equal button was clicked
     * Evaluate the expression
     */
    private void equalFun(){
        char temp = text.charAt(text.length() - 1);
        if(temp == '.' || temp == '+' || temp == '-' || temp == '×' || temp == '÷'){
            Toast.makeText(this, "Input Error!", Toast.LENGTH_SHORT).show();
            return;
        }

        StringBuffer str = null;
        StringBuffer ans = null;
        str = transferToPostfix(text);
        ans = calStr(str);
        clearFun();
        editText.setText(ans.toString());
    }

    /**
     * Get operator priority
     * Priority: * = / > + = -
     * @param ch The operator
     * @return The priority
     */
    private int getPriority(char ch){
        int ans = -1;
        switch(ch){
            case '+':
                ans = 1; break;
            case '-':
                ans = 1; break;
            case '×':
                ans = 2; break;
            case '÷':
                ans = 2;break;
        }
        return ans;
    }

    /**
     * Calculate the result of four base operations of two Numbers
     * @param num1 The first number
     * @param num2 The second number
     * @param fun The operator
     * @return The result
     */
    private BigDecimal cal(BigDecimal num1, BigDecimal num2, char fun){
        switch (fun){
            case '+' : return num1.add(num2);
            case '-' : return num1.subtract(num2);
            case '×' : return num1.multiply(num2);
            case '÷' : try{
                 return num1.divide(num2);
            }catch (Exception e)
            {
                return num1.divide(num2, 8, BigDecimal.ROUND_HALF_UP);
            }
        }
        return new BigDecimal(-1);
    }

    /**
     * Transfer the expression to the Reverse Polish notation
     * @param str The expression
     * @return The Reverse Polish notation
     */
    private StringBuffer transferToPostfix(StringBuffer str){
        StringBuffer ans = new StringBuffer("");
        StringBuffer num = new StringBuffer("");
        Stack<Character> s = new Stack<>();
        for(int i=0;i<str.length();i++){
            char temp = str.charAt(i);
            if(temp != '+' && temp != '-' && temp != '×' && temp != '÷'){
                num.append(temp);
            }
            else{
                if(!num.equals("")){
                    ans.append(num+" ");
                    num = new StringBuffer("");
                }
                int priority = getPriority(temp);
                while(!s.empty()){
                    int tmp = getPriority(s.peek());
                    if(tmp < priority)break;
                    ans.append(s.pop()+" ");
                }
                s.push(temp);
            }
        }
        if(!num.equals("")){
            ans.append(num+" ");
        }
        while(!s.empty()){
            ans.append(s.pop()+" ");
        }
        ans.deleteCharAt(ans.length() - 1);
        return ans;
    }

    /**
     * Evaluates the Reverse Polish notation
     * @param str The Reverse Polish notation
     * @return Expression result
     */
    private StringBuffer calStr(StringBuffer str){
        BigDecimal ans = new BigDecimal(0);
        Stack<BigDecimal> number = new Stack<>();
        StringBuffer num = new StringBuffer("");

        for(int i=0;i<str.length();i++){
            char temp = str.charAt(i);
            if(temp == ' '){
                if(num.length()<1)continue;
                number.push(new BigDecimal(num.toString()));
                num = new StringBuffer("");
            }
            else if(temp != '+' && temp != '-' && temp != '×' && temp != '÷'){
                num.append(temp);
            }
            else{
                BigDecimal num2 = number.pop();
                BigDecimal num1 = number.pop();


                if(temp == '÷' && num2.equals(new BigDecimal(0))){
                    return new StringBuffer("Error");
                }
                BigDecimal tmp = cal(num1, num2, temp);
                number.push(tmp);
                ans = tmp;
            }
        }

        return new StringBuffer(ans.toString()+"");
    }

}
