package org.example.mini_project_spring_boot.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.example.mini_project_spring_boot.entities.Member;
import org.example.mini_project_spring_boot.service.IServiceMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.beans.support.PagedListHolder.DEFAULT_PAGE_SIZE;


@Controller
@RequestMapping("/members")

public class MemberController {

    IServiceMember serviceMember;
    @Value("${upload.dir}")
    private  String uploadDir;


    public MemberController(IServiceMember serviceMember) {
        this.serviceMember = serviceMember;
    }

    @GetMapping
    public String viewMembers(Model model,
                              @RequestParam(defaultValue = "0") int page,
                              @RequestParam(defaultValue = "5") int size) {
        Page<Member> memberPage = serviceMember.getAllMembers(PageRequest.of(page, size));

        model.addAttribute("page", memberPage);
        model.addAttribute("members", memberPage.getContent());
        return "members";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("membre", new Member());
        return "member_form";
    }
    @GetMapping("/edit/{id}")
    public String showUpdateForm(Model model, @PathVariable Long id) {
        model.addAttribute("membre", new Member());
        Optional<Member> member= serviceMember.getMemberById(id);
        model.addAttribute("membre",member.get());
        return "member_form_update";
    }

    @PostMapping("/save")
    public String saveMember(@Valid @ModelAttribute("membre") Member membre,
                             BindingResult result,
                             @RequestParam("image") MultipartFile file) throws IOException {


        if (result.hasErrors()) {
            return "member_form";
        }

        if (!file.isEmpty() && !result.hasErrors()) {
            String sanitizedName = file.getOriginalFilename()
                    .replaceAll("[^a-zA-Z0-9.-]", "_")
                    .replaceAll("\\.\\.", "_");

            String fileName = UUID.randomUUID() + "_" + sanitizedName;
            Path uploadPath = Paths.get(uploadDir);

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            try {
                Files.copy(
                        file.getInputStream(),
                        uploadPath.resolve(fileName),
                        StandardCopyOption.REPLACE_EXISTING
                );
                membre.setImagePath(fileName);
            } catch (IOException e) {
                result.rejectValue("imagePath", "image.upload", "Échec de l'upload du fichier");
                return "member_form";
            }
        }

        serviceMember.save(membre);
        return "redirect:/members";
    }
    @PostMapping("/update/{id}")

    public String updateMember(@PathVariable Long id,
                               @ModelAttribute("membre") Member membre,
                               BindingResult result,
                               @RequestParam("image") MultipartFile file) throws IOException {


        if (result.hasErrors()) {
            return "member_form_update";
        }

        Optional<Member>  existingMember = serviceMember.getMemberById(id);

        if (!file.isEmpty() && !result.hasErrors()) {
            if (existingMember.get().getImagePath() != null) {
                Path oldImagePath = Paths.get(uploadDir).resolve(existingMember.get().getImagePath());
                try {
                    Files.deleteIfExists(oldImagePath);
                } catch (IOException e) {
                    result.rejectValue("imagePath", "image.delete", "Erreur lors de la suppression de l'ancienne image");
                    return "member_form_update";
                }
            }

            // Création de la nouvelle image
            String sanitizedName = file.getOriginalFilename()
                    .replaceAll("[^a-zA-Z0-9.-]", "_")
                    .replaceAll("\\.\\.", "_");

            String fileName = UUID.randomUUID() + "_" + sanitizedName;
            Path uploadPath = Paths.get(uploadDir);

            try {
                Files.createDirectories(uploadPath);
                Files.copy(
                        file.getInputStream(),
                        uploadPath.resolve(fileName),
                        StandardCopyOption.REPLACE_EXISTING
                );
                membre.setImagePath(fileName);
            } catch (IOException e) {
                result.rejectValue("imagePath", "image.upload", "Échec de l'upload du fichier");
                return "member_form_update";
            }
        } else {
            membre.setImagePath(existingMember.get().getImagePath());
        }

        serviceMember.updateMember(id, membre);
        return "redirect:/members/view/"+existingMember.get().getId();
    }
    @GetMapping("/delete/{id}")
    public String deleteMember(@PathVariable Long id) {
        serviceMember.deleteMember(id);
        return "redirect:/members";
    }

    @GetMapping("view/{id}")
    public String showMember(@PathVariable Long id,  Model model) {
        Optional<Member> member= serviceMember.getMemberById(id);

        model.addAttribute("membre",member.get());
        return "member_detail";
    }
    @GetMapping("/find")
    public String showFindForm(Model model,

                               @RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "5") int size,
                                String phoneNumber ) {
        Page<Member> memberPage;
        memberPage = serviceMember.findByPhoneNumberContaining(phoneNumber, PageRequest.of(page, size));

        model.addAttribute("page", memberPage);
        model.addAttribute("members", memberPage.getContent());
        return "members";
    }
}
