package com.eran.money;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedInterstitialAd;
import com.google.android.gms.ads.rewarded.RewardedInterstitialAdLoadCallback;

public class MainActivity extends AppCompatActivity {
    private RewardedInterstitialAd rewardedInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MobileAds.initialize(this, initializationStatus -> {});
        loadAd();

        Button openAdBtn = findViewById(R.id.openAdBtn);
        openAdBtn.setOnClickListener(view -> {
            if (rewardedInterstitialAd != null) {
                rewardedInterstitialAd.show(MainActivity.this, rewardItem -> {
                    Toast.makeText(MainActivity.this, "Ad completed! You earned reward.", Toast.LENGTH_SHORT).show();
                });
            } else {
                Toast.makeText(MainActivity.this, "Ad not ready", Toast.LENGTH_SHORT).show();
                loadAd();
            }
        });
    }

    private void loadAd() {
        AdRequest adRequest = new AdRequest.Builder().build();
        RewardedInterstitialAd.load(this, "ca-app-pub-9468972842132671/9821487412", adRequest, new RewardedInterstitialAdLoadCallback() {
            @Override
            public void onAdLoaded(@NonNull RewardedInterstitialAd ad) {
                rewardedInterstitialAd = ad;
            }
        });
    }
}
