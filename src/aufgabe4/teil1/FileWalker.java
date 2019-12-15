package teil1;

import java.io.File;
import java.util.ArrayList;

public class FileWalker {

    public ArrayList<File> getfilesList() {
        return _filesList;
    }

    private ArrayList<File> _filesList;

    public FileWalker() {
        _filesList = new ArrayList<>();
    }

    public void walk( String path ) {

        File root = new File( path );
        File[] list = root.listFiles();

        if (list == null) return;

        for ( File f : list ) {
            if ( f.isDirectory() ) {
                walk( f.getAbsolutePath() );
                //System.out.println( "Dir:" + f.getAbsoluteFile() );
            }
            else {
                _filesList.add(f);
                //System.out.println( "File:" + f.getAbsoluteFile() );
            }
        }
    }

/*    public void sortList() {
        for (int x = 0; x <= _filesList.size() - 1 ; x++) {
            File xFile = _filesList.get(x);
            for (int y = 0; y <= _filesList.size() - 1 ; y++) {
                File yFile = _filesList.get(y);
                if( xFile.length() < yFile.length()) {
                    _filesList.remove(x);
                    _filesList.add(x,yFile);
                    _filesList.remove(y);
                    _filesList.add(y,xFile);
                    break;
                }
            }
        }
    }*/

    public void sortList() {
        for (int x = 1; x < _filesList.size() ; x++) {
            for (int y = 0; y < _filesList.size() - x ; y++) {
                File xFile = _filesList.get(y);
                File yFile = _filesList.get(y + 1);
                if( xFile.length() < yFile.length()) {
                    _filesList.remove(y + 1);
                    _filesList.add(y + 1,xFile);
                    _filesList.remove(y);
                    _filesList.add(y,yFile);
                }
            }
        }
    }
}
