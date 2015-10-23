# FileManager

To your module:

      compile 'com.tuccro:filemanager:1.0.5-beta'

To your project:

        maven {
            url  "http://dl.bintray.com/tuccro/maven"
        }

Add an Activity to your AndroidManifest.xml:

        <activity android:name="com.tuccro.filemanager.ui.FileManagerActivity" />
 and permissions:
 
       <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
       <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
      
For file opening use in your activity:

            FileManager.getFile(YourActivity.this);
            
and for folder:

            FileManager.getFolder(YourActivity.this);

For result getting use in your Activity:

      @Override
      protected void onActivityResult(int requestCode, int resultCode, Intent data) {
      super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            String path;

            switch (requestCode) {
                case FileManager.REQUEST_FILE:

                    path = FileManager.getPathFromResultIntent(data);
                    break;
                case FileManager.REQUEST_FOLDER:

                    path = FileManager.getPathFromResultIntent(data);
                    break;
            }
        }
    }

[ ![Download](https://api.bintray.com/packages/tuccro/maven/FileManager/images/download.svg) ](https://bintray.com/tuccro/maven/FileManager/_latestVersion)
