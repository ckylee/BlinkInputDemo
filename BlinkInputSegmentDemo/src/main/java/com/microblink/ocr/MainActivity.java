package com.microblink.ocr;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.microblink.activity.SegmentScanActivity;
import com.microblink.help.HelpActivity;
import com.microblink.recognizers.blinkocr.engine.BlinkOCREngineOptions;
import com.microblink.recognizers.blinkocr.parser.generic.AmountParserSettings;
import com.microblink.recognizers.blinkocr.parser.generic.IbanParserSettings;
import com.microblink.recognizers.blinkocr.parser.regex.RegexParserSettings;
import com.microblink.recognizers.blinkocr.parser.topup.TopUpParserSettings;
import com.microblink.results.ocr.OcrFont;


public class MainActivity extends Activity {

    private static final int BLINK_OCR_REQUEST_CODE = 100;
    // obtain your licence key at http://microblink.com/login or
    // contact us at http://help.microblink.com
    private static final String LICENSE_KEY = "2Z7ZY7GF-VWRQWTPF-WBVW3E5V-AIUPI6NP-WJGFHEZV-V6ZEYU4T-GWX3F3DR-3WYUL6D6";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void uploadCSV () {
        //allow user to upload a csv file to be parsed after scanning
    }

    /**
     * Called as handler for "custom scan ui integration" button.
     */
    public void advancedIntegration(View v) {
        // advanced integration example is given in ScanActivity source code
        Intent intent = new Intent(this, ScanActivity.class);
        startActivity(intent);
    }

    public void advancedColorIntegration(View v) {
        // advanced integration example is given in ScanActivity source code
        Intent intent = new Intent(this, ScanActivity.class);


        // same as in simple integration example, we will invoke scanning on SegmentScanActivity,
        // so we need to setup an Intent for it.

        // now let's setup OCR engine parameters for scanning colored texts:
        BlinkOCREngineOptions engineOptions = new BlinkOCREngineOptions();
        // only uppercase chars and digits are allowed. Don't waste time on classifying other characters as we
        // do not need them.
        //engineOptions.addAllDigitsToWhitelist(OcrFont.OCR_FONT_ANY).addUppercaseCharsToWhitelist(OcrFont.OCR_FONT_ANY);
        // do not bother with text lines that are smaller than 40 pixels
        //engineOptions.setMinimumLineHeight(40);
        // we expect the VIN to be black text, so we can drop all colors from image - this will give better accuracy
        // because coloured text will be automatically discarded -- disabled
        engineOptions.setColorDropoutEnabled(false);

        // now let's create a RegexParser
        //RegexParserSettings regexParserSettings = new RegexParserSettings("[A-Z0-9]{17}", engineOptions);


        // optionally, if we want the help screen to be available to user on camera screen,
        // we can simply prepare an intent for help activity and pass it to SegmentScanActivity
        Intent helpIntent = new Intent(this, HelpActivity.class);
        intent.putExtra(SegmentScanActivity.EXTRAS_HELP_INTENT, helpIntent);

        startActivity(intent);
    }


}
