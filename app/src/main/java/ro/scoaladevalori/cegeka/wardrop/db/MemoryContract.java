package ro.scoaladevalori.cegeka.wardrop.db;

import android.provider.BaseColumns;

        public class MemoryContract {
            private MemoryContract() {
            }


            public static final class MemoryEntry implements BaseColumns {
                public static final String TABLE_NAME = "memories";
                public static final String COLUMN_COLOR = "color";
                public static final String COLUMN_CATEGORY = "category";
                public static final String COLUMN_STYLE = "style";
                public static final String COLUMN_IMAGE = "image";
            }
        }