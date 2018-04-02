package harkat.jussi.loppuharkkaproto;

import android.content.Context;

import harkat.jussi.loppuharkkaproto.pages.CustomerInfoPage;
import harkat.jussi.loppuharkkaproto.pages.DatePickerPage;
import harkat.jussi.loppuharkkaproto.pages.EndTimePage;
import harkat.jussi.loppuharkkaproto.pages.PainPage;
import harkat.jussi.loppuharkkaproto.pages.StartTimePage;

import com.tech.freak.wizardpager.model.AbstractWizardModel;
import com.tech.freak.wizardpager.model.BranchPage;
import com.tech.freak.wizardpager.model.MultipleFixedChoicePage;
import com.tech.freak.wizardpager.model.NumberPage;
import com.tech.freak.wizardpager.model.PageList;
import com.tech.freak.wizardpager.model.SingleFixedChoicePage;
import com.tech.freak.wizardpager.model.TextPage;

public class SandwichWizardModel extends AbstractWizardModel {
    public SandwichWizardModel(Context context) {
        super(context);
    }

    @Override
    protected PageList onNewRootPageList() {
        return new PageList(new DatePickerPage(this, "Päivämäärä").setRequired(false),
                new StartTimePage(this, "Kohtauksen alku").setRequired(false),
                new EndTimePage(this, "Kohtauksen loppu").setRequired(false),
                new PainPage(this, "Kivun taso").setRequired(false),
                new MultipleFixedChoicePage(this, "Tyyppi").setChoices(
                        "Aurallinen",
                        "Auraton"),
                new BranchPage(this, "Kivun sijainti")
                        .addBranch("Vasen puoli")
                        .addBranch("Oikea puoli")
                        .addBranch(
                                "Kuvaile itse",
                                new TextPage(this, "Kivun sijainti").setRequired(false)),
                new MultipleFixedChoicePage(this, "Kivun tyyppi").setChoices(
                        "Viiltävä", "Sykkivä", "Pakottava"),
                new BranchPage(this, "Onko muita oireita?")
                        .addBranch("Ei")
                        .addBranch(
                                "Kyllä",
                                new TextPage(this, "Kuvaile oireita").setRequired(false)),
                new BranchPage(this, "Lääkkeet")
                        .addBranch("Ei otettu")
                        .addBranch(
                                "Otettu",
                                new TextPage(this, "Lääkkeen nimi ja annostus").setRequired(false)
                                , new SingleFixedChoicePage(this, "Lääkkeen vaikutus").setChoices(
                                        "Tehokas", "Jokseenkin toimii", "Tehoton")
                        ),
                new TextPage(this, "Sijainti kohtauksen alkaessa").setRequired(false),
                new MultipleFixedChoicePage(this, "Ennakko-oireet").setChoices(
                        "Näköharhat", "Valonherkkyys", "Ääniherkkyys", "Hajuherkkyys"),
                new BranchPage(this, "Onko muita ennako-oireita?")
                        .addBranch("Ei")
                        .addBranch(
                                "Kyllä",
                                new TextPage(this, "Kuvaile ennakko-oireita").setRequired(false)),
                new MultipleFixedChoicePage(this, "Laukaisevat tekijät").setChoices(
                        "Stressi", "Muutos unirytmissä", "Levottomuus", "Kuukautiskierto"),
                new BranchPage(this, "Onko muita laukaisevia tekijäitä??")
                        .addBranch("Ei")
                        .addBranch(
                                "Kyllä",
                                new TextPage(this, "Kuvaile laukaisevia tekijöitä").setRequired(false))
        );
    }
}
