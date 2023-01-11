package com.selab.auction.member.repository;

import com.selab.auction.member.model.entity.MemberRole;
import com.selab.auction.member.model.vo.MemberRoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRoleRepository extends JpaRepository<MemberRole, Long> {
    Optional<MemberRole> findByName(MemberRoleName roleName);
}
