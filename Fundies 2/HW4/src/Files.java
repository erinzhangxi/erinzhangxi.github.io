// CS 2510 Fall 2015
// Assignment 4

// Zhang Xi
// xizhang2
// Yifan Xing
// xingyif

import tester.*;

// to represent different files in a computer
interface IFile {

    // compute the size of this file
    int size();

    // compute the time (in seconds) to download this file
    // at the given download rate
    int downloadTime(int rate);

    // is the owner of this file the same 
    // as the owner of the given file?
    boolean sameOwner(IFile that);

    // get the owner of this IFile
    String getOwner();
}

// to represent the abstract class AFile
abstract class AFile implements IFile {
    String name;
    String owner;

    // constructor
    AFile(String name, String owner) {
        this.name = name;
        this.owner = owner;
    }

    // compute the size of this file
    public abstract int size();

    // compute the time (in seconds) to download this file
    // at the given download rate
    public int downloadTime(int rate) {
        if ((this.size() / rate ) % 10 == 0) {
            return this.size() / rate;
        }
        else {
            return this.size() / rate + 1; 
        }
    }

    // get the owner of this IFile
    public String getOwner() {
        return this.owner;
    }

    // is the owner of this file the same 
    // as the owner of the given file?
    public boolean sameOwner(IFile that) {
        return this.owner.equals(that.getOwner());
    }
}

// to represent a text file
class TextFile extends AFile {
    int length;   // in bytes

    // constructor for a Text file
    TextFile(String name, String owner, int length) {
        super(name, owner);
        this.length = length;
    }

    // compute the size of this file
    public int size() {
        return this.length;
    }  
}

//to represent an image file
class ImageFile extends AFile { 
    int width;   // in pixels
    int height;  // in pixels

    // constructor for a Image file
    ImageFile(String name, String owner, int width, int height) {
        super(name, owner);
        this.width = width;
        this.height = height;
    }

    // compute the size of this file
    public int size() {
        return this.width * this.height;
    }  
}


//to represent an audio file
class AudioFile extends AFile {
    int speed;   // in bytes per second
    int length;  // in seconds of recording time

    // constructor for a Audio file
    AudioFile(String name, String owner, int speed, int length) {
        super(name, owner);
        this.speed = speed;
        this.length = length;
    }

    // compute the size of this file
    public int size() {
        return this.speed * this.length;
    }  
}

// a class for examples
class ExamplesFiles {

    IFile text1 = new TextFile("English paper", "Maria", 1234);
    IFile picture = new ImageFile("Beach", "Maria", 400, 200);
    IFile song = new AudioFile("Help", "Pat", 200, 120);

    IFile text2 = new TextFile("US News", "Annie", 2000);
    IFile picture1 = new ImageFile("sand", "Annie", 300, 300);
    IFile song1 = new AudioFile("Song", "Jack", 200, 200);

    // test the method size for the classes that represent files
    boolean testSize(Tester t) {
        return
                t.checkExpect(this.text1.size(), 1234) &&
                t.checkExpect(this.picture.size(), 80000) &&
                t.checkExpect(this.song.size(), 24000) &&
                t.checkExpect(this.text2.size(), 2000) &&
                t.checkExpect(this.picture1.size(), 90000) &&
                t.checkExpect(this.song1.size(), 40000);
    }

    // test the method size for the classes that represent files
    boolean testDownloadTime(Tester t) {
        return
                t.checkExpect(this.text1.downloadTime(100), 13) &&
                t.checkExpect(this.picture.downloadTime(200), 400) &&
                t.checkExpect(this.song.downloadTime(2400), 10) &&
                t.checkExpect(this.text2.downloadTime(200), 10) &&
                t.checkExpect(this.picture1.downloadTime(300), 300) &&
                t.checkExpect(this.song1.downloadTime(400), 100);
    }


    // test the method sameOwner for the classes that represent files
    boolean testSameOwner(Tester t) {
        return
                t.checkExpect(this.text1.sameOwner(this.picture), true) &&
                t.checkExpect(this.picture.sameOwner(this.song), false) &&
                t.checkExpect(this.song.sameOwner(this.song1), false);
    }

}