package filetree;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class Directory extends File {
	private final List<File> files;
	public Directory(Path path, List<File> files) {
		super(path);
		this.files = files;
	}
	@Override
	public Iterator<File> iterator() {
		File header = this;
		final Iterator<File> mainIterator=files.iterator();
		final Iterator<File>[] SubIterator = new Iterator[]{null};
		return new Iterator<File>() {
			boolean first = true;
			@Override
			public boolean hasNext() {
				return (first ||mainIterator.hasNext() || (SubIterator[0] != null && SubIterator[0].hasNext()));
			}
			@Override
			public File next() {
				if(!hasNext())
					throw new NoSuchElementException();
				if(first) {
					first= false;
					return header;
				}
				if(SubIterator[0] != null && SubIterator[0].hasNext()){
					File tempo = SubIterator[0].next();
					if(!SubIterator[0].hasNext()){
						SubIterator[0]=null;
					}
					return tempo;
				}
				File temp = mainIterator.next();
				if(temp.isRegularFile()){
					return temp;
				} else{
					SubIterator[0] =temp.iterator();
				}
				return SubIterator[0].next();
			}
		};

	}
	@Override
	public int getHeight() {
		int result = 0;
		for (File p : this.files) {
			if(result<1+p.getHeight()){
				result= 1 + p.getHeight();
			}
		}
		return result;
	}
	@Override
	public boolean isRegularFile() {
		return false;
	}
	public List<File> getFiles() {
		return files;
	}
}
