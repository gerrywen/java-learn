package f.command.commandmode.command;

import f.command.commandmode.device.Stereo;

public class StereoSubVolCommand implements Command {
    private Stereo setreo;

    public StereoSubVolCommand(Stereo setreo) {
        this.setreo = setreo;
    }

    @Override
    public void execute() {

        int vol = setreo.GetVol();
        if (vol > 0) {
            setreo.SetVol(--vol);
        }
    }

    @Override
    public void undo() {
        int vol = setreo.GetVol();
        if (vol < 11) {
            setreo.SetVol(++vol);
        }

    }
}
