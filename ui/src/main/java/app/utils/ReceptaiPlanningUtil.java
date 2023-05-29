package app.utils;

import app.model.Produktas;
import app.model.Receptas;
import app.model.Ribojimas;
import app.view.RibojimasView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class ReceptaiPlanningUtil {

    private static final int DAYS = 7;
    public static List<Receptas> planReceptaiForWeek(List<RibojimasView> ribojimai, List<Receptas> receptai) {
        Collections.shuffle(receptai);
        List<Receptas> result = new ArrayList<>();
        boolean hasResult = plan(ribojimai, receptai, 0, result);
        return !hasResult ? Collections.EMPTY_LIST : result;
    }

    private static boolean plan(List<RibojimasView> ribojimai, List<Receptas> receptai, int step, List<Receptas> result) {
        if (DAYS == step) {
            return true;
        }
        for (int i = 0; i < receptai.size(); i++) {
            List<Receptas> tempResult = new ArrayList<>(result);
            result.add(receptai.get(i));
            if (isValidForAllRibojimai(ribojimai, result)) {
                receptai.remove(i);
                if (plan(ribojimai, receptai, step + 1, result)) {
                    return true;
                }
            } else {
                result.remove(result.size() - 1);
            }
        }
        return false;

    }

    private static boolean isValidForAllRibojimai(List<RibojimasView> ribojimai, List<Receptas> receptai) {
        for (RibojimasView ribojimas : ribojimai) {
            Long produktasId = ribojimas.getProduktasId();
            if (getProduktoKiekisPlane(receptai, produktasId) > ribojimas.getTimesPerWeek()) {
                return false;
            }
        }
        return true;
    }

    private static int getProduktoKiekisPlane(List<Receptas> receptai, Long produktasId) {
        int count = 0;
        for (Receptas receptas : receptai) {
            count = receptas.getProduktai().stream().anyMatch(p -> Objects.equals(p.getId(), produktasId)) ? count + 1 : count;
        }
        return count;
    }
}
