package ru.spsuace.homework2.objects.analyzer;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;


//Filter tries using the key decrypt given text. In case of success returns true. Algorithm of encryption is BlowFish
public class CustomAnalyzer extends Analyzer {
    public String Key;

    CustomAnalyzer(String key) {
        super(FilterType.CUSTOM);
        Key = key;
    }

    @Override
    public boolean ApplyFilter(String Text) {
        try {
            SecretKeySpec skeyspec = new SecretKeySpec(Key.getBytes(),"Blowfish");
            Cipher cipher = Cipher.getInstance("Blowfish");
            cipher.init(Cipher.DECRYPT_MODE, skeyspec);
            byte[] decrypted = cipher.doFinal(Text.getBytes());
            return  true;
        } catch (Exception e) {
            return  false;
        }
    }
}
