package filetree;

import java.nio.file.Path;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RegularFile extends File {
	public RegularFile(Path path) {
		super(path);
	}
	@Override
	public Iterator<File> iterator() {
		File toIterate= this;
		return new Iterator<File>() {
			Boolean answer = true;
			@Override
			public boolean hasNext() {
				return answer;
			}
			@Override
			public File next() {
				if(answer){
					answer=false;
					return toIterate;
				}
				throw new NoSuchElementException();
			}
		};
	}
	@Override
	public boolean isRegularFile() {
		return true;
	}
	@Override
	public int getHeight() {
		return 0;
	}
}
