package f.command.commandmode.command;

import f.command.commandmode.device.Stereo;

public class StereoOnCommand implements Command {
    private Stereo setreo;

    public StereoOnCommand(Stereo setreo) {
        this.setreo = setreo;
    }

    @Override
    public void execute() {
        setreo.On();
        setreo.SetCd();
    }

    @Override
    public void undo() {
        setreo.Off();
    }

}
