
public interface IMemory {

	public int size();

	public int get(int pc);

	public void set(int pc, int value);

	public void set(int pc, int[] buffer);

	public void clear();

}