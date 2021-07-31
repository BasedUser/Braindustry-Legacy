package ModVars.Classes.UI.Cheat;

import arc.Core;
import arc.func.Cons;
import arc.scene.ui.Image;
import arc.scene.ui.ImageButton;
import arc.scene.ui.layout.Cell;
import arc.scene.ui.layout.Scl;
import arc.struct.Seq;
import mindustry.game.Team;
import mindustry.gen.Tex;
import mindustry.ui.Styles;
import mindustry.ui.dialogs.BaseDialog;

import static mindustry.Vars.mobile;

public class TeamChooseDialog extends BaseDialog {
    private Cons<Team> confirm;

    public TeamChooseDialog(Cons<Team> confirm) {
        super("Choose team:");
        this.confirm = confirm;
        setup();
        if (mobile) onResize(this::setup);
        this.addCloseButton();
    }

    private void setup() {
        cont.clear();
        this.cont.table(i -> {
            i.table(t -> {
                final int buttonSize = 20;
                int pad = 6,counter=0;
                float coln = mobile ? (Core.graphics.getWidth()-20)/Scl.scl(buttonSize+pad) : 20;
//                coln = !mobile ? 20 : (Core.graphics.getWidth() - Scl.scl((2) * pad)) / Scl.scl(buttonSize + pad);
//                coln = Math.max(1, coln);
                for (Team team : Team.all) {
                    if (++counter % coln == 0) t.row();
                    ImageButton button = new ImageButton(Tex.whitePane, Styles.clearToggleTransi);
                    button.clearChildren();
                    Image image = new Image();
                    button.background(image.getDrawable()).setColor(team.color);
                    button.add(image).color(team.color).size(buttonSize);
                    button.clicked(() -> {
                        confirm.get(team);
                        this.hide();
                    });
                    t.add(button).color(team.color).width(buttonSize).height(buttonSize).pad(pad);
                }
            });
        }).growX().bottom().center();
    }
}
