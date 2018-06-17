package ro.scoaladevalori.cegeka.wardrop.db;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.util.Base64;

import java.io.ByteArrayOutputStream;


    public class Memory {
        private static final float PREFERRED_WIDTH = 250;
        private static final float PREFERRED_HEIGHT = 250;
        private String color;
        private String image;
        private String style;
        private String category;
        //private int minim;
        //private int maxim;


        public static final int COL_ID = 0;
        public static final int COL_COLOR = 1;
        public static final int COL_CATEGORY= 2;
        public static final int COL_STYLE = 3;
        public static final int COL_IMAGE = 4;
        //public static final int COL_MINIM = 5;
        //public static final int COL_MAXIM = 6;

        public Memory(Cursor cursor) {
            this.color = cursor.getString(COL_COLOR);
            this.category = cursor.getString(COL_CATEGORY);
            this.style = cursor.getString(COL_STYLE);
            this.image = cursor.getString(COL_IMAGE);
            //this.minim = cursor.getInt(COL_MINIM);
            //this.maxim = cursor.getInt(COL_MAXIM);
        }

        public Memory(String color, String category, String style, Bitmap image) {
            this.color = color;
            this.category = category;
            this.style = style;
            this.image = bitmapToString(resizeBitmap(image));
           // this.minim = minim;
            //this.maxim = maxim;
        }

        public String getColor() {
            return this.color;
        }

        public Bitmap getImage() {
            return stringToBitmap(this.image);
        }

        public String getStyle(){return this.style; }

        public String getCategory() {return this.category;}

        public String getImageAsString() {
            return this.image;
        }

        //public int getMinim(){return this.minim;}

        //public int getMaxim(){return this.maxim;}

        private static String bitmapToString(Bitmap bitmap) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
            byte[] b = baos.toByteArray();
            return Base64.encodeToString(b, Base64.DEFAULT);
        }

        private static Bitmap stringToBitmap(String encodedString) {
            try {
                byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
                return BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            } catch (Exception e) {
                e.getMessage();
                return null;
            }
        }

        public static Bitmap resizeBitmap(Bitmap bitmap) {
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            float scaleWidth = PREFERRED_WIDTH / width;
            float scaleHeight = PREFERRED_HEIGHT / height;

            Matrix matrix = new Matrix();
            matrix.postScale(scaleWidth, scaleHeight);
            Bitmap resizedBitmap = Bitmap.createBitmap(
                    bitmap, 0, 0, width, height, matrix, false);
            bitmap.recycle();
            return resizedBitmap;
        }
    }

