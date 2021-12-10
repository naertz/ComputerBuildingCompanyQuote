package computerbuilding.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import computerbuilding.beans.SoundCard;
import computerbuilding.service.SoundCardServiceInterface;

@Controller
public class SoundCardController {
	@Autowired
	SoundCardServiceInterface soundCardService;

	@GetMapping("/sound_cards")
	public String viewSoundCardsPage(final Model model) {
		return findPaginated(1, "name", "asc", model);
	}

	@GetMapping("/sound_cards/add_sound_card")
	public String addSoundCard(final Model model) {
		final SoundCard soundCard = new SoundCard();
		model.addAttribute("soundCard", soundCard);
		return "new_sound_card";
	}

	@PostMapping("/sound_cards/update_sound_card")
	public String updateSoundCard(@ModelAttribute("soundCard") final SoundCard soundCard) {
		soundCardService.updateSoundCard(soundCard);
		return "redirect:/sound_cards";
	}

	@GetMapping("/sound_cards/edit_sound_card/{id}")
	public String editSoundCard(@PathVariable(value = "id") final long id, final Model model) {
		final SoundCard soundCard = soundCardService.getSoundCardById(id);
		model.addAttribute("soundCard", soundCard);
		return "edit_sound_card";
	}

	@GetMapping("/sound_cards/delete_sound_card/{id}")
	public String deleteSoundCard(@PathVariable(value = "id") final long id) {
		soundCardService.deleteSoundCardById(id);
		return "redirect:/sound_cards";
	}

	@GetMapping("/sound_cards/page/{pageNumber}")
	public String findPaginated(@PathVariable(value = "pageNumber") final int pageNumber, @RequestParam("sortField") final String sortField, @RequestParam("sortDirection") final String sortDirection, final Model model) {
		final int pageSize = 5;
		final Page<SoundCard> soundCardPage = soundCardService.findPaginated(pageNumber, pageSize, sortField, sortDirection);
		final List<SoundCard> soundCards = soundCardPage.getContent();
		model.addAttribute("soundCardPage", pageNumber);
		model.addAttribute("totalSoundCardPages", soundCardPage.getTotalPages());
		model.addAttribute("totalSoundCardItems", soundCardPage.getTotalElements());
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDirection", sortDirection);
		model.addAttribute("reverseSortDirection", "asc".equals(sortDirection) ? "desc" : "asc");
		model.addAttribute("soundCards", soundCards);
		return "sound_cards";
	}
}
